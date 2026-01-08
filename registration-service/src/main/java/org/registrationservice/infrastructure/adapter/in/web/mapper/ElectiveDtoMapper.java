package org.registrationservice.infrastructure.adapter.in.web.mapper;

import org.mapstruct.Mapper;
import org.registrationservice.domain.model.Elective;
import org.registrationservice.infrastructure.adapter.in.web.dto.ElectiveEventResponseDto;
import org.registrationservice.infrastructure.adapter.in.web.dto.ElectiveResponseDto;

@Mapper(componentModel = "spring")
public interface ElectiveDtoMapper {

    ElectiveResponseDto toResponseDto(Elective elective);

    ElectiveEventResponseDto toEventResponseDto(Elective elective);
}
