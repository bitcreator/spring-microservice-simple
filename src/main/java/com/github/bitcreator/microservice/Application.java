package com.github.bitcreator.microservice;

import com.github.bitcreator.microservice.infrastructure.kafka.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.github.bitcreator.microservice")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
