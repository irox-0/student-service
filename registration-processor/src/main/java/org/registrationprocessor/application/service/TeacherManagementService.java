package org.registrationprocessor.application.service;

import lombok.RequiredArgsConstructor;
import org.commonlibs.event.TeacherFoundEvent;
import org.commonlibs.event.TeacherSearchEvent;
import org.registrationprocessor.application.mapper.TeacherEventMapper;
import org.registrationprocessor.domain.port.in.TeacherSearchEventConsumerPort;
import org.registrationprocessor.domain.port.out.TeacherFoundEventProducerPort;
import org.registrationprocessor.domain.port.out.TeacherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TeacherManagementService implements TeacherSearchEventConsumerPort {

    private final TeacherRepository repository;
    private final TeacherFoundEventProducerPort port;
    private final TeacherEventMapper mapper;

    @Value("${teacher-not-found}")
    private String teacherNotFound;

    @Override
    public void consume(TeacherSearchEvent event) {
        var optionalTeacher = repository.findTeacherBySubject(event.subject());
        var foundEvent = optionalTeacher
                .map(teacher -> mapper.toFoundEvent(event, teacher))
                .orElseGet(() -> mapper.toNotFoundEvent(event, teacherNotFound));
        port.produce(foundEvent);
    }
}
