package org.registrationprocessor.infrastructure.adapter.out.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commonlibs.event.TeacherFoundEvent;
import org.registrationprocessor.domain.port.out.TeacherFoundEventProducerPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaTeacherFoundEventProducer implements TeacherFoundEventProducerPort {


    private final KafkaTemplate<String, TeacherFoundEvent> template;

    @Override
    public void produce(TeacherFoundEvent event) {
        template.send(
                "teacher-found",
                event.id(),
                event
        ).thenAccept(result ->
                log.info("TeacherFoundEvent sent to kafka: id={}", event.id()));
    }
}
