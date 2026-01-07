package org.registrationservice.domain.port.out;

import org.registrationservice.domain.model.Elective;

public interface ElectiveEventPublisher {
    void publish(Elective elective);
}
