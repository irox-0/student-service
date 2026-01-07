package org.registrationservice.domain.event;

import org.registrationservice.domain.model.UniversitySubject;

public record TeacherSearchEvent(
        UniversitySubject subject
) {
}
