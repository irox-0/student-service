package org.registrationservice.infrastructure.adapter.out.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commonlibs.event.ElectiveStatus;
import org.commonlibs.event.TeacherSearchEvent;
import org.registrationservice.domain.mapper.ElectiveEventMapper;
import org.registrationservice.domain.model.Elective;
import org.registrationservice.domain.port.out.ElectiveEventProducerPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaElectiveEventProducer implements ElectiveEventProducerPort {

    private final KafkaTemplate<String, TeacherSearchEvent> template;
    private final ElectiveEventMapper mapper;

    @Override
    public void produce(Elective elective) {
        elective.setStatus(ElectiveStatus.APPROVAL_PENDING);
        TeacherSearchEvent event = mapper.toTeacherSearchEvent(elective);

        log.info("mapped event from elective: {}", event);
        template.send(
                "teacher-search",
                event.id(),
                event
        ).thenAccept(result ->
                log.info("Event sent to kafka: id={}", event.id()));
    }
}
