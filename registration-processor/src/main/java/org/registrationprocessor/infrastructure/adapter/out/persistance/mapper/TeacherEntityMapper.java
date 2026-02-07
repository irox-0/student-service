package org.registrationprocessor.infrastructure.adapter.out.persistance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.registrationprocessor.domain.model.Teacher;
import org.registrationprocessor.infrastructure.adapter.out.persistance.entity.TeacherEntity;

@Mapper(componentModel = "spring")
public interface TeacherEntityMapper {

    @Mapping(target = "id", ignore = true)
    TeacherEntity toEntity(Teacher teacher);

    @Mapping(target = "id", ignore = true)
    Teacher toModel(TeacherEntity entity);
}
