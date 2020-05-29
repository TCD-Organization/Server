package fr.tcd.server.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    public static final String AUTH_EXCHANGE = "tcd-";
    public static final String AUTH_QUEUE = "auth-queue";

    @Bean
    Queue authQueue() {
        return QueueBuilder
                .durable("auth-queue")
                //.deadLetterExchange()
                //.lazy() // Force la queue a flusher les msg sur le disque plutôt que de tout garder en RAM
                .build();
    }

    @Bean
    TopicExchange authExchange() {
        return new TopicExchange(AUTH_EXCHANGE);
    }

    @Bean
    Binding authBinding() {
        return BindingBuilder.bind(authQueue()).to(authExchange()).with("#");
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) { // Ce paramètre est chargé car il existe dans ce contexte, il faut mettre rabbitTemplate à la fin
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}