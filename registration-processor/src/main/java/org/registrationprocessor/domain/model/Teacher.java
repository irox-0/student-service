package org.registrationprocessor.domain.model;

import lombok.Builder;
import lombok.Data;
import org.commonlibs.event.UniversitySubject;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class Teacher {
    private UUID id;
    private String name;
    private UniversitySubject subject;
    private LocalDateTime possibleTime;
}
