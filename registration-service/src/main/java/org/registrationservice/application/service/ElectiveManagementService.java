package org.registrationservice.application.service;

import org.registrationservice.domain.model.Elective;
import org.registrationservice.domain.model.UniversitySubject;
import org.registrationservice.domain.port.in.ElectiveManagementPort;
import org.springframework.stereotype.Service;

@Service
public class ElectiveManagementService implements ElectiveManagementPort {

    @Override
    public Elective create(UniversitySubject subject) {
        return null;
    }

    @Override
    public Elective get(UniversitySubject subject) {
        return null;
    }
}
