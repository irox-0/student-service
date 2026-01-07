package org.registrationservice.domain.port.out;

import org.registrationservice.domain.model.Elective;
import org.registrationservice.domain.model.UniversitySubject;

public interface ElectiveRepository {
    void save(Elective elective);
    Elective read(UniversitySubject subject);
}
