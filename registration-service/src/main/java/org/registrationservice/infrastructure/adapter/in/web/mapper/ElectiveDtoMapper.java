package org.registrationservice.infrastructure.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.registrationservice.domain.model.Elective;
import org.registrationservice.infrastructure.adapter.in.web.dto.ElectiveEventResponseDto;
import org.registrationservice.infrastructure.adapter.in.web.dto.ElectiveResponseDto;

@Mapper(componentModel = "spring")
public interface ElectiveDtoMapper {

//    @Mapping(source = "id", ignore = true)
//    @Mapping(source = "date", ignore = true)
//    @Mapping(target = "teacherName", ignore = true)
    ElectiveResponseDto toResponseDto(Elective elective);

//    @Mapping(target = "id", ignore = true)
    ElectiveEventResponseDto toEventResponseDto(Elective elective);
}
