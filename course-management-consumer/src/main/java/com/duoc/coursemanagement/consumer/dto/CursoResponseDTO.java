package com.duoc.coursemanagement.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursoResponseDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private String instructor;
    private LocalDate fechaInicio;
    private String estado;
    private String contenidoS3Key;
}
