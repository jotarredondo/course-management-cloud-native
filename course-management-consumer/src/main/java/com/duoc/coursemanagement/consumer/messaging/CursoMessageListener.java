package com.duoc.coursemanagement.consumer.messaging;

import com.duoc.coursemanagement.consumer.config.RabbitMQConfig;
import com.duoc.coursemanagement.consumer.dto.CursoRequestDTO;
import com.duoc.coursemanagement.consumer.dto.CursoResponseDTO;
import com.duoc.coursemanagement.consumer.service.CursoService;
import com.duoc.coursemanagement.messaging.CursoMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CursoMessageListener {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CursoMessageListener.class);

    private final CursoService cursoService;

    public CursoMessageListener(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @RabbitListener(queues = RabbitMQConfig.CURSO_CREAR_QUEUE)
    public void recibirCreacionCurso(CursoMessageDTO mensaje) {

        CursoRequestDTO requestDTO = new CursoRequestDTO(
                mensaje.getTitulo(),
                mensaje.getDescripcion(),
                mensaje.getInstructor(),
                mensaje.getFechaInicio(),
                mensaje.getEstado(),
                mensaje.getContenidoS3Key()
        );

        CursoResponseDTO cursoCreado =
                cursoService.crearCurso(requestDTO);

        LOGGER.info(
                "Curso creado desde RabbitMQ. ID: {}, título: {}",
                cursoCreado.getId(),
                cursoCreado.getTitulo()
        );
    }
}