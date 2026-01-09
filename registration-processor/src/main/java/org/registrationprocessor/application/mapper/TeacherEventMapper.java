package org.registrationprocessor.application.mapper;

import org.commonlibs.event.TeacherFoundEvent;
import org.commonlibs.event.TeacherSearchEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.registrationprocessor.domain.model.Teacher;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface TeacherEventMapper {

    @Mapping(target = "id", source = "event.id")
    @Mapping(target = "subject", source = "event.subject")
    @Mapping(target = "teacherName", source = "teacher.name")
    @Mapping(target = "date", source = "teacher.possibleTime")
    TeacherFoundEvent toFoundEvent(TeacherSearchEvent event, Teacher teacher);


    default TeacherFoundEvent toNotFoundEvent(TeacherSearchEvent event, String notFoundMassage) {
        return TeacherFoundEvent.builder()
                .id(event.id())
                .subject(event.subject())
                .teacherName(notFoundMassage)
                .date(LocalDateTime.now())
                .build();
    }
}
