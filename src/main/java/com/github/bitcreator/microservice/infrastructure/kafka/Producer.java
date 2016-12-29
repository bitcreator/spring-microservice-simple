package com.github.bitcreator.microservice.infrastructure.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class Producer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produceMessage(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        future.addCallback(
            new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable ex) {
                    logger.error(
                        String.format("(%s) Unable to produce message='{}'", getClass().getName()),
                        message,
                        ex
                    );
                }

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    logger.info(
                        String.format("(%s) Produced message='{}' with offset={}", getClass().getName()),
                        message,
                        result.getRecordMetadata().offset()
                    );
                }
            }
        );
    }
}
