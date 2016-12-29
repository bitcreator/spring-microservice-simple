package com.github.bitcreator.microservice.infrastructure.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "microservice.events")
    public void consumeMessage(String message) {
        logger.info(String.format("(%s) Consumed message='{}'", getClass().getName()), message);

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
