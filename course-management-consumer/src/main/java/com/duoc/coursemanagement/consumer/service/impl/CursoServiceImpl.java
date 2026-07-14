package com.duoc.coursemanagement.consumer.service.impl;


import com.duoc.coursemanagement.consumer.dto.CursoRequestDTO;
import com.duoc.coursemanagement.consumer.dto.CursoResponseDTO;
import com.duoc.coursemanagement.consumer.exception.ResourceNotFoundException;
import com.duoc.coursemanagement.consumer.model.Curso;
import com.duoc.coursemanagement.consumer.repository.CursoRepository;
import com.duoc.coursemanagement.consumer.service.CursoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<CursoResponseDTO> listarCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    @Override
    public CursoResponseDTO obtenerCursoPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No se encontró el curso con ID: " + id
                        )
                );

        return convertirAResponseDTO(curso);
    }

    private CursoResponseDTO convertirAResponseDTO(Curso curso) {
        return new CursoResponseDTO(
                curso.getId(),
                curso.getTitulo(),
                curso.getDescripcion(),
                curso.getInstructor(),
                curso.getFechaInicio(),
                curso.getEstado(),
                curso.getContenidoS3Key()
        );
    }

    @Override
    public CursoResponseDTO crearCurso(CursoRequestDTO cursoRequestDTO) {

        Curso curso = new Curso();

        curso.setTitulo(cursoRequestDTO.getTitulo());
        curso.setDescripcion(cursoRequestDTO.getDescripcion());
        curso.setInstructor(cursoRequestDTO.getInstructor());
        curso.setFechaInicio(cursoRequestDTO.getFechaInicio());
        curso.setEstado(cursoRequestDTO.getEstado());
        curso.setContenidoS3Key(cursoRequestDTO.getContenidoS3Key());

        Curso cursoGuardado = cursoRepository.save(curso);

        return convertirAResponseDTO(cursoGuardado);
    }

    @Override
    public CursoResponseDTO actualizarCurso(
            Long id,
            CursoRequestDTO cursoRequestDTO
    ) {

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No se encontró el curso con ID: " + id
                        )
                );

        curso.setTitulo(cursoRequestDTO.getTitulo());
        curso.setDescripcion(cursoRequestDTO.getDescripcion());
        curso.setInstructor(cursoRequestDTO.getInstructor());
        curso.setFechaInicio(cursoRequestDTO.getFechaInicio());
        curso.setEstado(cursoRequestDTO.getEstado());
        curso.setContenidoS3Key(cursoRequestDTO.getContenidoS3Key());

        Curso cursoActualizado = cursoRepository.save(curso);

        return convertirAResponseDTO(cursoActualizado);
    }

    @Override
    public void eliminarCurso(Long id) {

        if (!cursoRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "No se encontró el curso con ID: " + id
            );
        }

        cursoRepository.deleteById(id);
    }



}