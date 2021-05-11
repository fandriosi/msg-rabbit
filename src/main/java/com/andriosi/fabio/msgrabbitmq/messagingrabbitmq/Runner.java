package com.andriosi.fabio.msgrabbitmq.messagingrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message");
        rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName,"foo.bar.baz",
                "Hello from RabbitMQ! to Fabio Andriosi");
        receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
    }
}
