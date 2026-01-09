package org.registrationservice.infrastructure.adapter.in.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commonlibs.event.TeacherFoundEvent;
import org.registrationservice.domain.port.in.ElectiveEventConsumerPort;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@Slf4j
@RequiredArgsConstructor
public class KafkaElectiveEventConsumer {

    private final ElectiveEventConsumerPort port;

    @KafkaListener(
            topics = "teacher-found",
            containerFactory = "teacherFoundEventListenerFactory"
    )
    public void listen(TeacherFoundEvent event) {
        log.info("Listened event: {}", event);
        port.consume(event);
    }
}
