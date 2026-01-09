package org.registrationservice.domain.port.in;


import org.commonlibs.event.TeacherFoundEvent;

public interface ElectiveEventConsumerPort {
    void consume(TeacherFoundEvent event);
}
