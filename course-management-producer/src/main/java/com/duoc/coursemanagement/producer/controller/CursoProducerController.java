package com.duoc.coursemanagement.producer.controller;

import com.duoc.coursemanagement.messaging.CursoMessageDTO;
import com.duoc.coursemanagement.producer.dto.OperacionResponseDTO;
import com.duoc.coursemanagement.producer.service.CursoProducerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cursos")
public class CursoProducerController {

    private final CursoProducerService cursoProducerService;

    public CursoProducerController(
            CursoProducerService cursoProducerService
    ) {
        this.cursoProducerService = cursoProducerService;
    }

    @PostMapping
    public ResponseEntity<OperacionResponseDTO> crearCurso(
            @Valid @RequestBody CursoMessageDTO mensaje
    ) {

        cursoProducerService.enviarCreacionCurso(mensaje);

        return ResponseEntity.accepted().body(
                new OperacionResponseDTO(
                        "Solicitud de creación de curso enviada",
                        "curso.crear"
                )
        );
    }
}
