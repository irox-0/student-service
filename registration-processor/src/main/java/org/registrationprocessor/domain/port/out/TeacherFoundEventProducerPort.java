package org.registrationprocessor.domain.port.out;


import org.commonlibs.event.TeacherFoundEvent;

public interface TeacherFoundEventProducerPort {
    void producer(TeacherFoundEvent event);
}
