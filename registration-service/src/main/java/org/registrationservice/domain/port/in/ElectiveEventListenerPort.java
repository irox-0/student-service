package org.registrationservice.domain.port.in;

import org.registrationservice.domain.event.TeacherFoundEvent;

public interface ElectiveEventListenerPort {
    void listen(TeacherFoundEvent event);
}
