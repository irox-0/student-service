package org.commonlibs.event;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TeacherFoundEvent(
        String id,
        String teacherName,
        LocalDateTime time
) {}
