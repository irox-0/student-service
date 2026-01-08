package org.registrationservice.domain.model;

import lombok.Builder;
import lombok.Data;
import org.commonlibs.event.ElectiveStatus;
import org.commonlibs.event.UniversitySubject;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Elective {
    private UUID id;
    private UniversitySubject subject;
    private LocalDateTime date;
    private String teacherName;
    private ElectiveStatus status;
}
