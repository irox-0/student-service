package org.registrationservice.infrastructure.adapter.in.web.dto;

import org.commonlibs.event.UniversitySubject;

public record ElectiveRequestDto(
        UniversitySubject subject
) {
}
