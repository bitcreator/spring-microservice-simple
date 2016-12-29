package com.github.bitcreator.microservice;

import com.github.bitcreator.microservice.infrastructure.kafka.Consumer;
import com.github.bitcreator.microservice.infrastructure.kafka.Producer;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

    @Test
    public void testProducer() throws InterruptedException {
        // When:
        producer.produceMessage("microservice.events", "Hello Spring Kafka!");

        // Then:
        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        Assertions.assertThat(consumer.getLatch().getCount()).isEqualTo(0);
    }
}
