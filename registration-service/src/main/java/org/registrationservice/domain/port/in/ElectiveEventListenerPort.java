package org.registrationservice.domain.port.in;


import org.commonlibs.event.TeacherFoundEvent;

public interface ElectiveEventListenerPort {
    void listen(TeacherFoundEvent event);
}
