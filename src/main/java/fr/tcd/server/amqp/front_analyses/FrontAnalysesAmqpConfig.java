package fr.tcd.server.amqp.front_analyses;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FrontAnalysesAmqpConfig {
    public static final String EXCHANGE = "type.id.tx";

    @Bean
    TopicExchange FrontAnalysesExchange() {
        return new TopicExchange(EXCHANGE);
    }

}