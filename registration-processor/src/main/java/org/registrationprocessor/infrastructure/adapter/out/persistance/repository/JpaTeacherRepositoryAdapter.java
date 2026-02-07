package org.registrationprocessor.infrastructure.adapter.out.persistance.repository;

import lombok.RequiredArgsConstructor;
import org.commonlibs.event.UniversitySubject;
import org.registrationprocessor.domain.model.Teacher;
import org.registrationprocessor.domain.port.out.TeacherRepository;
import org.registrationprocessor.infrastructure.adapter.out.persistance.mapper.TeacherEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaTeacherRepositoryAdapter implements TeacherRepository {

    private final JpaTeacherRepository repository;
    private final TeacherEntityMapper mapper;

    @Override
    public Optional<Teacher> findTeacherBySubject(UniversitySubject subject) {
        var optionalEntity = repository.findTeacherEntityBySubject(subject);
        return optionalEntity.map(mapper::toModel);
    }
}
