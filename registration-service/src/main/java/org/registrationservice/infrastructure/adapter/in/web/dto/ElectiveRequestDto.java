package org.registrationservice.infrastructure.adapter.in.web.dto;

import org.registrationservice.domain.model.UniversitySubject;

public record ElectiveRequestDto(
        UniversitySubject subject
) {
}
