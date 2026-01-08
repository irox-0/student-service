package org.registrationservice.domain.port.in;

import org.registrationservice.domain.model.Elective;
import org.registrationservice.domain.model.UniversitySubject;

import java.util.Optional;


public interface ElectiveManagementPort {
    Elective create(UniversitySubject subject);
    Optional<Elective> get(UniversitySubject subject);
}
