package org.registrationservice.domain.port.out;

import org.commonlibs.event.UniversitySubject;
import org.registrationservice.domain.model.Elective;

import java.util.Optional;

public interface ElectiveRepository {
    Elective save(Elective elective);
    Optional<Elective> read(UniversitySubject subject);

}
