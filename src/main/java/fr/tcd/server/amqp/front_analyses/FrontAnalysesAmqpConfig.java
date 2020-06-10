package fr.tcd.server.amqp.front_analyses;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

import static fr.tcd.server.amqp.AmqpConfig.EXCHANGE;

@Configuration
public class FrontAnalysesAmqpConfig {
    public static final String EXCHANGE = "type.id.tx";

    @Bean
    TopicExchange FrontAnalysesExchange() {
        return new TopicExchange(EXCHANGE);
    }

}