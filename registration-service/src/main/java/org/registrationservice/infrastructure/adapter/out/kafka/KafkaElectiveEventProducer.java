package org.registrationservice.infrastructure.adapter.out.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commonlibs.event.TeacherSearchEvent;
import org.registrationservice.domain.port.out.ElectiveEventProducerPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaElectiveEventProducer implements ElectiveEventProducerPort {

    private final KafkaTemplate<String, TeacherSearchEvent> template;

    @Override
    public void produce(TeacherSearchEvent event) {
        template.send(
                "teacher-search",
                event.id(),
                event
        ).thenAccept(result ->
                log.info("Event sent to kafka: id={}", event.id()));
    }
}
