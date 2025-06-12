package com.fenix.flash.fenixflash.controller;

import com.fenix.flash.fenixflash.model.Curso;
import com.fenix.flash.fenixflash.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/cursos")
@CrossOrigin(origins = {"http://localhost:3000"})
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Curso> findAll() {
        return service.findAll();
    }

    @GetMapping(path = {"/search", "/pesquisar"})
    public ResponseEntity<List<Curso>> search(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        List<Curso> result = service.search(query, page, size);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}
