package com.duoc.coursemanagement.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OperacionResponseDTO {

    private String mensaje;
    private String routingKey;
}
