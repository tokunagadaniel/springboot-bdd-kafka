package br.com.tokunaga.controller;

import br.com.tokunaga.service.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    @Value("${spring.kafka.producer.topic2.name}")
    private String topic;
    private final Producer topicProducer;

    @GetMapping(value = "/kafka/send")
    public String send() {
        String uuid = UUID.randomUUID().toString();

        topicProducer.send("my-topic-2", "Mensagem de teste enviada ao tópico: " + uuid);

        return uuid;
    }
}