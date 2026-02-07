package org.registrationprocessor.infrastructure.adapter.out.persistance.repository;

import org.commonlibs.event.UniversitySubject;
import org.registrationprocessor.infrastructure.adapter.out.persistance.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTeacherRepository extends JpaRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findTeacherEntityBySubject(UniversitySubject subject);
}
