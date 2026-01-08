package org.registrationservice.domain.port.out;

import org.registrationservice.domain.model.Elective;
import org.registrationservice.domain.model.UniversitySubject;

import java.util.Optional;

public interface ElectiveRepository {
    Elective save(Elective elective);
    Optional<Elective> read(UniversitySubject subject);

}
