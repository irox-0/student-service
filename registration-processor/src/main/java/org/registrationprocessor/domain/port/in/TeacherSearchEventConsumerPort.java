package org.registrationprocessor.domain.port.in;


import org.commonlibs.event.TeacherSearchEvent;

public interface TeacherSearchEventConsumerPort {
    void consume(TeacherSearchEvent event);
}
