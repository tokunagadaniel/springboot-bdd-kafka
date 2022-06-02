package br.com.tokunaga.bdd;

import br.com.tokunaga.Application;
import br.com.tokunaga.service.Producer;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("bdd")
@SpringBootTest(classes = Application.class)
@CucumberContextConfiguration
@EmbeddedKafka(
        partitions = 1,
        topics = "my-topic-3"
)
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
        consumer.subscribe(Collections.singletonList("my-topic-3"));
    }

    @Given("que o sistema A enviou uma mensagem")
    public void send() {
        producer.send("my-topic-2", "Test");
    }

    @And("que será validada com sucesso")
    public void change() {

    }

    @When("aplicação receber essa mensagem")
    public void receive() {

    }

    @Then("produzirá uma mensagem ao Sistema B")
    public void check() {
        ConsumerRecord<String, String> cr = KafkaTestUtils.getSingleRecord(consumer, "my-topic-3");

        assertEquals("Test", cr.value());
    }
}