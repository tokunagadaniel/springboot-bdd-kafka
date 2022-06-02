package br.com.tokunaga.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    @Value("${spring.kafka.producer.topic3.name}")
    private String topic;

    private final Producer producer;

    @KafkaListener(topics = "${spring.kafka.consumer.topic.name}", groupId = "test")
    public void consume(ConsumerRecord<String, String> payload) {
        producer.send(topic, payload.value());
    }
}