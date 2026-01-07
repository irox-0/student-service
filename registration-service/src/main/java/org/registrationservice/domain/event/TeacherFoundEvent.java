package org.registrationservice.domain.event;

import java.time.LocalDateTime;

public record TeacherFoundEvent(
        String teacher,
        LocalDateTime time
) {}
