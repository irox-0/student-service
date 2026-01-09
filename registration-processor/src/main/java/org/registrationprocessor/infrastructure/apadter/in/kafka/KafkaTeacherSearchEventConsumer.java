package org.registrationprocessor.infrastructure.apadter.in.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commonlibs.event.TeacherSearchEvent;
import org.registrationprocessor.domain.port.in.TeacherSearchEventConsumerPort;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@Slf4j
@RequiredArgsConstructor
public class KafkaTeacherSearchEventConsumer {

    private final TeacherSearchEventConsumerPort port;

    @KafkaListener(
            topics = "teacher-search",
            containerFactory = "teacherSearchEventListenerFactory"
    )
    public void listen(TeacherSearchEvent event) {
        log.info("Listened event: {}", event);
        port.consume(event);
    }
}
