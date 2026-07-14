package com.duoc.coursemanagement.consumer.service;

import com.duoc.coursemanagement.consumer.dto.CursoRequestDTO;
import com.duoc.coursemanagement.consumer.dto.CursoResponseDTO;

import java.util.List;

public interface CursoService {
    List<CursoResponseDTO> listarCursos();

    CursoResponseDTO obtenerCursoPorId(Long id);

    CursoResponseDTO crearCurso(CursoRequestDTO cursoRequestDTO);

    CursoResponseDTO actualizarCurso(
            Long id,
            CursoRequestDTO cursoRequestDTO);

    void eliminarCurso(Long id);
}
