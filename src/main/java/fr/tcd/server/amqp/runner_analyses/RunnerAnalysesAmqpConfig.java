package fr.tcd.server.amqp.runner_analyses;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RunnerAnalysesAmqpConfig {

    public static final String EXCHANGE = "type.state.tx";
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