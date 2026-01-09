package org.registrationservice.application.mapper;

import org.commonlibs.event.TeacherSearchEvent;
import org.mapstruct.Mapper;
import org.registrationservice.domain.model.Elective;

@Mapper(componentModel = "spring")
public interface ElectiveEventMapper {

    TeacherSearchEvent toTeacherSearchEvent(Elective elective);

}
