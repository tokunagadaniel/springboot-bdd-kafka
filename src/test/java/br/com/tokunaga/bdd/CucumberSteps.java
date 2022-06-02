package br.com.tokunaga.bdd;

import br.com.tokunaga.service.Producer;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@CucumberContextConfiguration
@EmbeddedKafka(topics = "my-topic-3")
public class CucumberSteps {

    @Autowired
    private Producer producer;

    @Autowired
    private ConsumerFactory consumerFactory;

    private Consumer consumer;

    @Before
    public void setUp() {
        DefaultKafkaConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<>(consumerFactory.getConfigurationProperties());
        consumer = cf.createConsumer();
        consumer.subscription();
    }

    @Dado("que o sistema A enviou uma mensagem {string}")
    public void send(String mensagem) throws Throwable {
        producer.send("my-topic-2", mensagem);
    }

    @E("que será validada com sucesso")
    public void scenario() {

    }

    @Quando("aplicação receber essa mensagem")
    public void receive() {

    }

    @Entao("produzirá uma mensagem ao Sistema B")
    public void check(String message) {
        ConsumerRecord<String, String> cr = KafkaTestUtils.getSingleRecord(consumer, "my-topic-3");

        assertEquals(message, cr.value());
    }
}