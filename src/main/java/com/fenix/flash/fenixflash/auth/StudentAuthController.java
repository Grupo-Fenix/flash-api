package com.fenix.flash.fenixflash.auth;

import com.fenix.flash.fenixflash.dto.StudentRegistration;
import com.fenix.flash.fenixflash.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/auth/student")
@CrossOrigin(origins = {"http://localhost:3000"})
public class StudentAuthController {

    private record AuthResponse(String message) {
    }

    private final StudentService service;

    public StudentAuthController(StudentService service) {
        this.service = service;
    }

    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<?> register(@Valid @RequestBody StudentRegistration request, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessages = errors.getAllErrors().stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, errorMessages);
            return ResponseEntity.badRequest().body(problemDetail);
        } else {
            service.upsert(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("Conta criada com sucesso"));
        }
    }
}
