package org.registrationservice.domain.port.in;

import org.registrationservice.domain.event.TeacherFoundEvent;

public interface ElectiveEventListener {
    void listen(TeacherFoundEvent event);
}
