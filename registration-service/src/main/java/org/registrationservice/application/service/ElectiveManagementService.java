package org.registrationservice.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.registrationservice.domain.model.Elective;
import org.registrationservice.domain.model.ElectiveStatus;
import org.registrationservice.domain.model.UniversitySubject;
import org.registrationservice.domain.port.in.ElectiveManagementPort;
import org.registrationservice.domain.port.out.ElectiveRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ElectiveManagementService implements ElectiveManagementPort {

    private final ElectiveRepository repository;

    @Override
    public Elective create(UniversitySubject subject) {
        Elective elective = Elective.builder()
                .id(UUID.randomUUID())
                .subject(subject)
                .status(ElectiveStatus.SUBMITTED)
                .build();

        log.info("Elective created: elective={}", elective);
        return repository.save(elective);
    }

    public Elective save(Elective elective) {
        return repository.save(elective);
    }

    @Override
    public Optional<Elective> get(UniversitySubject subject) {
        return repository.read(subject);
    }
}
