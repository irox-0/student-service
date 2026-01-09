package org.registrationservice.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.registrationservice.domain.model.Elective;
import org.registrationservice.infrastructure.adapter.out.persistence.entity.ElectiveEntity;

@Mapper(componentModel = "spring")
public interface ElectiveEntityMapper {

    ElectiveEntity toEntity(Elective elective);

    Elective toModel(ElectiveEntity elective);
}
