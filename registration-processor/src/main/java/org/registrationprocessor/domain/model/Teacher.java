package org.registrationprocessor.domain.model;

import lombok.Builder;
import org.commonlibs.event.UniversitySubject;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public class Teacher {
    private UUID id;
    private String name;
    private UniversitySubject subject;
    private LocalDateTime possibleTime;
}
