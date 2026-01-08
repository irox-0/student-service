package org.registrationservice.domain.port.in;

import org.commonlibs.event.UniversitySubject;
import org.registrationservice.domain.model.Elective;

import java.util.Optional;


public interface ElectiveManagementPort {
    Elective create(UniversitySubject subject);
    Optional<Elective> get(UniversitySubject subject);
}
