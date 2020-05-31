package fr.tcd.server.amqp.runner_analyses;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static fr.tcd.server.amqp.AmqpConfig.EXCHANGE;

@Configuration
public class RunnerAnalysesAmqpConfig {

    private static final String QUEUE = "new_runner_analyses_q";

    @Bean
    Queue runnerAnalysisQueue() {
        return QueueBuilder
                .durable(QUEUE)
                .build();
    }

    @Bean
    TopicExchange runnerAnalysisExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    Binding runnerAnalysisBinding() {
        return BindingBuilder.bind(runnerAnalysisQueue()).to(runnerAnalysisExchange()).with("runner_analyses.new");
    }
}