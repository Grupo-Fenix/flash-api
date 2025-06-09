package com.fenix.flash.fenixflash.controller;

import com.fenix.flash.fenixflash.model.Teacher;
import com.fenix.flash.fenixflash.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping
    public List<?> findAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    @PostAuthorize("hasRole('ROLE_ROOT')")
    public ResponseEntity<Teacher> findById(@PathVariable long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public void create(@RequestBody Teacher teacher) {
        service.upsert(teacher);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public void update(@RequestBody Teacher teacher) {
        service.upsert(teacher);
    }


    @DeleteMapping
    @PreAuthorize("hasAuthority('DELETE')")
    public void delete(@RequestBody Teacher teacher) {
        service.delete(teacher);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

}
