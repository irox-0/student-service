package org.registrationservice.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Elective {
    private UUID id;
    private UniversitySubject subject;
    private LocalDateTime date;
    private String teacher;
}
