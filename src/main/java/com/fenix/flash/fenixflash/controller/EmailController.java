package com.fenix.flash.fenixflash.controller;

import com.fenix.flash.fenixflash.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
@CrossOrigin(origins = {"http://localhost:3000"})
public class EmailController {
    public record Email(String email) {
    }

    public record Response(boolean exists) {
    }

    private final StudentService service;

    public EmailController(StudentService service) {
        this.service = service;
    }

    @PostMapping(path = "/email-check", consumes = "application/json")
    public ResponseEntity<?> verify(@RequestBody Email email) {
        return ResponseEntity.ok(new Response(service.exists(email.email)));
    }

}
