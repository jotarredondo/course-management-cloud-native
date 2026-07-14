package com.duoc.coursemanagement.consumer.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String EXCHANGE =
            "course.management.exchange";

    public static final String CURSO_CREAR_QUEUE =
            "curso.crear.queue";

    public static final String CURSO_CREAR_ROUTING_KEY =
            "curso.crear";

    @Bean
    public DirectExchange courseManagementExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue crearCursoQueue() {
        return QueueBuilder
                .durable(CURSO_CREAR_QUEUE)
                .build();
    }

    @Bean
    public Binding crearCursoBinding(
            Queue crearCursoQueue,
            DirectExchange courseManagementExchange
    ) {
        return BindingBuilder
                .bind(crearCursoQueue)
                .to(courseManagementExchange)
                .with(CURSO_CREAR_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
