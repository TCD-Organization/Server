package fr.tcd.server.amqp.front_analyses;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static fr.tcd.server.amqp.AmqpConfig.EXCHANGE;

@Configuration
public class FrontAnalysesAmqpConfig {
    private static final String QUEUE = "all_front_analyses_q";

    @Bean
    Queue frontAnalysesQueue() {
        return QueueBuilder
                .durable(QUEUE)
                .build();
    }

    @Bean
    TopicExchange FrontAnalysesExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    Binding FrontAnalysesBinding() {
        return BindingBuilder.bind(frontAnalysesQueue()).to(FrontAnalysesExchange()).with("front_analyses.#");
    }
}