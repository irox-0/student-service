package org.registrationservice.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.registrationservice.domain.model.Elective;
import org.registrationservice.infrastructure.adapter.out.persistence.entity.ElectiveEntity;

@Mapper(componentModel = "spring")
public interface ElectiveEntityMapper {

    @Mapping(target = "id", ignore = true)
    ElectiveEntity toEntity(Elective elective);

    @Mapping(target = "id", ignore = true)
    Elective toModel(ElectiveEntity elective);
}
