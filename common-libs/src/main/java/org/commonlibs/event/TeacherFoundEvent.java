package org.commonlibs.event;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TeacherFoundEvent(
        String id,
        UniversitySubject subject,
        String teacherName,
        LocalDateTime date
) {}
