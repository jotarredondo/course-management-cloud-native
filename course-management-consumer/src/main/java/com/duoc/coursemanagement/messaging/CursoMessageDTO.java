package com.duoc.coursemanagement.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursoMessageDTO {

    private String titulo;
    private String descripcion;
    private String instructor;
    private LocalDate fechaInicio;
    private String estado;
    private String contenidoS3Key;
}