package com.fenix.flash.fenixflash.auth;

import com.fenix.flash.fenixflash.dto.AuthenticationService;
import com.fenix.flash.fenixflash.dto.TeacherRegistration;
import com.fenix.flash.fenixflash.jwt.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth/teacher")
@CrossOrigin(origins = {"http://localhost:3000"})
public class TeacherAuthController {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final AuthenticationService authenticationService;

    public TeacherAuthController(UserDetailsService userDetailsService, JwtService jwtService, AuthenticationManager authManager, AuthenticationService authenticationService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(), request.password()
        ));
        UserDetails details = userDetailsService.loadUserByUsername(request.email());
        String token = jwtService.generateToken(details);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/register", consumes = "multipart/form-data")
    public void register(@Valid @ModelAttribute TeacherRegistration teacher) {
        authenticationService.registerTeacher(teacher);
    }

}
