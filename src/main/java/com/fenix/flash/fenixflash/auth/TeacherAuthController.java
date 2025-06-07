package com.fenix.flash.fenixflash.auth;

import com.fenix.flash.fenixflash.jwt.JwtService;
import com.fenix.flash.fenixflash.model.Teacher;
import com.fenix.flash.fenixflash.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/auth/teacher")
public class TeacherAuthController {

    private final TeacherService teacherService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public TeacherAuthController(TeacherService teacherService, JwtService jwtService, AuthenticationManager authManager) {
        this.teacherService = teacherService;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(), request.password()
        ));
        UserDetails details = teacherService.findByUsername(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("Username '" + request.email() + "' does not exists"));
        String token = jwtService.generateToken(details);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/register")
    public void register(@RequestBody Teacher teacher) {
        teacherService.upsert(teacher);
    }

}
