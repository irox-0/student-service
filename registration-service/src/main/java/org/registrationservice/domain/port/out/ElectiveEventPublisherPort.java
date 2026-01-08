package org.registrationservice.domain.port.out;

import org.registrationservice.domain.model.Elective;

public interface ElectiveEventPublisherPort {
    void publish(Elective elective);
}
