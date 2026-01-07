package org.registrationservice.domain.port.in;

import org.registrationservice.domain.model.Elective;
import org.registrationservice.domain.model.UniversitySubject;

public interface ElectiveManagementPort {
    Elective create(UniversitySubject subject);
    Elective get(UniversitySubject subject);
}
