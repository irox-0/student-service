package org.registrationservice.infrastructure.adapter.in.web.dto;

import org.registrationservice.domain.model.ElectiveStatus;
import org.registrationservice.domain.model.UniversitySubject;

public record ElectiveResponseDto(
        UniversitySubject subject,
        ElectiveStatus status
) {
}
