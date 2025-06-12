package com.fenix.flash.fenixflash.repository;

import com.fenix.flash.fenixflash.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Page<Curso> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
