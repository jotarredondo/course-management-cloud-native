package com.duoc.coursemanagement.consumer.repository;

import com.duoc.coursemanagement.consumer.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByInstructorIgnoreCase(String instructor);

    List<Curso> findByEstadoIgnoreCase(String estado);
}
