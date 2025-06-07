package com.fenix.flash.fenixflash.controller;

import com.fenix.flash.fenixflash.model.Teacher;
import com.fenix.flash.fenixflash.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping(path = "teachers")
    public ResponseEntity<List<Teacher>> findAll() {
        var result = service.findAll();
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "teachers/find/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/teachers/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Teacher teacher) {
        service.upsert(teacher);
    }

    @DeleteMapping(path = "teachers/delete/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    @DeleteMapping(path = "/teachers/delete")
    public void delete(@RequestBody Teacher teacher) {
        service.delete(teacher);
    }

}
