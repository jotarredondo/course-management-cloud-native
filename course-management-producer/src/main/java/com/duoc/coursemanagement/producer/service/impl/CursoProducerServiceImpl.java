package com.duoc.coursemanagement.producer.service.impl;

import com.duoc.coursemanagement.messaging.CursoMessageDTO;
import com.duoc.coursemanagement.producer.config.RabbitMQConfig;
import com.duoc.coursemanagement.producer.service.CursoProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class CursoProducerServiceImpl
        implements CursoProducerService {

    private final RabbitTemplate rabbitTemplate;

    public CursoProducerServiceImpl(
            RabbitTemplate rabbitTemplate
    ) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void enviarCreacionCurso(CursoMessageDTO mensaje) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.CURSO_CREAR_ROUTING_KEY,
                mensaje
        );
    }
}
