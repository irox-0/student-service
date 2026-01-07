package org.registrationservice.infrastructure.adapter.in.dto;

import org.registrationservice.domain.model.UniversitySubject;

public record ElectiveRequestDto(
        UniversitySubject subject
) {
}
