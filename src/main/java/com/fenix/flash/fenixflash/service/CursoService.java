package com.fenix.flash.fenixflash.service;

import com.fenix.flash.fenixflash.model.Curso;
import com.fenix.flash.fenixflash.repository.CursoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<Curso> findAll() {
        return repository.findAll();
    }

    public List<Curso> search(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findByTituloContainingIgnoreCase(query, pageable)
                .stream().toList();
    }

}
