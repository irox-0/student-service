package org.registrationprocessor.domain.port.out;

import org.commonlibs.event.UniversitySubject;
import org.registrationprocessor.domain.model.Teacher;

import java.util.Optional;

public interface TeacherRepository {
    Optional<Teacher> findTeacherBySubject(UniversitySubject subject);
}
