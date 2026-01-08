package org.registrationservice.domain.port.out;

import org.registrationservice.domain.model.Elective;

public interface ElectiveEventProducerPort {
    void produce(Elective elective);
}
