package com.duoc.coursemanagement.producer.service;


import com.duoc.coursemanagement.messaging.CursoMessageDTO;

public interface CursoProducerService {

    void enviarCreacionCurso(CursoMessageDTO mensaje);
}
