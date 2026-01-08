package org.commonlibs.event;

import lombok.Builder;

@Builder
public record TeacherSearchEvent(
        String id,
        UniversitySubject subject
) {
}
