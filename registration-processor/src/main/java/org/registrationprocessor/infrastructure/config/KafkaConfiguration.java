package org.registrationprocessor.infrastructure.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.commonlibs.event.TeacherFoundEvent;
import org.commonlibs.event.TeacherSearchEvent;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Bean
    ConsumerFactory<String, TeacherSearchEvent> teacherSearchEventConsumerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> consumerProperties =  kafkaProperties.buildConsumerProperties();
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
        consumerProperties.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "org.commonlibs.event");
        return new DefaultKafkaConsumerFactory<>(consumerProperties);
    }

    @Bean
    KafkaListenerContainerFactory<?> teacherSearchEventListenerFactory(ConsumerFactory<String, TeacherSearchEvent> teacherSearchEventConsumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, TeacherSearchEvent>();
        factory.setConsumerFactory(teacherSearchEventConsumerFactory);
        factory.setBatchListener(false);
        return factory;
    }


    @Bean
    ProducerFactory<String, TeacherFoundEvent> teacherFoundEventProducerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> producerProperties = kafkaProperties.buildProducerProperties();
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(producerProperties);
    }

    @Bean
    KafkaTemplate<String, TeacherFoundEvent> teacherFoundEventKafkaTemplate(
            ProducerFactory<String, TeacherFoundEvent> teacherFoundEventProducerFactory
    ) {
        return new KafkaTemplate<>(teacherFoundEventProducerFactory);
    }
}
