package com.fenix.flash.fenixflash.auth;

import com.fenix.flash.fenixflash.auth.AuthResponse.Success;
import com.fenix.flash.fenixflash.dto.UserRegistration;
import com.fenix.flash.fenixflash.jwt.JwtService;
import com.fenix.flash.fenixflash.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtService jwtService,
            UserService userService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(request.email());
        } catch (UsernameNotFoundException e) {
            return badRequest().body(new AuthResponse.Error(UNAUTHORIZED.value(), e.getMessage()));
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        String jwtToken = jwtService.generateToken(userDetails);
        return ok(new Success(jwtToken));
    }

    @PostMapping("/verify-email")
    public ResponseEntity<EmailUsernameVerification> verifyEmail(@RequestBody String email) {
        boolean exists = userService.emailExists(email);
        String msg;
        if (exists) {
            msg = "Este email já existe";
        } else {
            msg = "Este email está disponível";
        }
        return ok(new EmailUsernameVerification(exists, msg));
    }

    @PostMapping("/verify-username")
    public ResponseEntity<EmailUsernameVerification> verifyUsername(@RequestBody String username) {
        boolean exists = userService.usernameExists(username);
        String msg;
        if (exists) {
            msg = "Este nome de utilizador já existe";
        } else {
            msg = "Este nome de utilizador está disponível";
        }
        return ok(new EmailUsernameVerification(exists, msg));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistration request, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                               .map(ObjectError::getDefaultMessage)
                               .collect(Collectors.joining(". "));
            ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, msg);
            return ResponseEntity.of(detail).build();
        }
        userService.register(request);
        return ResponseEntity.status(CREATED).build();
    }

}
