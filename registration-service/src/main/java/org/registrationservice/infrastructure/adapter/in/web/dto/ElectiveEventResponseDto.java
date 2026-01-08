package org.registrationservice.infrastructure.adapter.in.web.dto;

import org.registrationservice.domain.model.ElectiveStatus;
import org.registrationservice.domain.model.UniversitySubject;

import java.time.LocalDateTime;

public record ElectiveEventResponseDto(
        UniversitySubject subject,
        LocalDateTime date,
        String teacherName,
        ElectiveStatus status
) {
}
