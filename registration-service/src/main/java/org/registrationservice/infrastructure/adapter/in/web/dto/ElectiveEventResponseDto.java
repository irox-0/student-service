package org.registrationservice.infrastructure.adapter.in.web.dto;

import org.commonlibs.event.ElectiveStatus;
import org.commonlibs.event.UniversitySubject;

import java.time.LocalDateTime;

public record ElectiveEventResponseDto(
        UniversitySubject subject,
        LocalDateTime date,
        String teacherName,
        ElectiveStatus status
) {
}
