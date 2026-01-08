package org.registrationservice.infrastructure.adapter.in.web.dto;

import org.commonlibs.event.ElectiveStatus;
import org.commonlibs.event.UniversitySubject;

public record ElectiveResponseDto(
        UniversitySubject subject,
        ElectiveStatus status
) {
}
