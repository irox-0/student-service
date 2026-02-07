package org.registrationservice.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commonlibs.event.ElectiveStatus;
import org.commonlibs.event.TeacherFoundEvent;
import org.commonlibs.event.TeacherSearchEvent;
import org.commonlibs.event.UniversitySubject;
import org.registrationservice.application.mapper.ElectiveEventMapper;
import org.registrationservice.domain.model.Elective;
import org.registrationservice.domain.port.in.ElectiveEventConsumerPort;
import org.registrationservice.domain.port.in.ElectiveManagementPort;
import org.registrationservice.domain.port.out.ElectiveEventProducerPort;
import org.registrationservice.domain.port.out.ElectiveRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ElectiveManagementService implements ElectiveManagementPort, ElectiveEventConsumerPort {

    private final ElectiveRepository repository;
    private final ElectiveEventProducerPort publisherPort;
    private final ElectiveEventMapper mapper;

    @Value("${teacher-not-found}")
    private String teacherNotFound;

    @Override
    public Elective create(UniversitySubject subject) {
        Elective elective = Elective.builder()
                .id(UUID.randomUUID())
                .subject(subject)
                .status(ElectiveStatus.SUBMITTED)
                .build();

        log.info("Elective created: elective={}", elective);

        elective.setStatus(ElectiveStatus.APPROVAL_PENDING);
        TeacherSearchEvent event = mapper.toTeacherSearchEvent(elective);
        publisherPort.produce(event);
        return repository.save(elective);
    }

    public Elective save(Elective elective) {
        return repository.save(elective);
    }

    @Override
    public Optional<Elective> get(UniversitySubject subject) {
        return repository.read(subject);
    }

    @Override
    public void consume(TeacherFoundEvent event) {
        var optionalElective = get(event.subject());
        if (optionalElective.isEmpty()) {
            log.warn("Received TeacherFoundEvent for subject {}, but no elective found in database. Skipping.", event.subject());
            return;
        }
        updateElectiveInfo(optionalElective.get(), event);
    }

    private void updateElectiveInfo(Elective elective, TeacherFoundEvent event) {
        elective
                .setDate(event.date())
                .setTeacherName(event.teacherName())
                .setStatus(
                        event.teacherName().equals(teacherNotFound) ?
                                ElectiveStatus.REJECTED :
                                ElectiveStatus.SCHEDULED
                );
        save(elective);
    }
}
