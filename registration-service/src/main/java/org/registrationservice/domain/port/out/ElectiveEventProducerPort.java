package org.registrationservice.domain.port.out;

import org.commonlibs.event.TeacherSearchEvent;

public interface ElectiveEventProducerPort {
    void produce(TeacherSearchEvent event);
}
