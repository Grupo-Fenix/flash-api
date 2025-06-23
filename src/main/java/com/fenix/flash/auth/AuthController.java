package com.fenix.flash.auth;

import com.fenix.flash.auth.AuthResponse.Error;
import com.fenix.flash.auth.AuthResponse.Success;
import com.fenix.flash.auth.jwt.JwtService;
import com.fenix.flash.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

import static com.fenix.flash.util.FlashConstants.RESOURCE_DOES_NOT_EXISTS;
import static com.fenix.flash.util.FlashConstants.USERNAME_EXISTS;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;
import static org.springframework.http.ResponseEntity.*;

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

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody LoginRequest request
    ) {
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(request.email());
        } catch (UsernameNotFoundException e) {
            return badRequest().body(new AuthResponse.Error(UNAUTHORIZED.value(), e.getMessage()));
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (AuthenticationException ex) {
            return badRequest().body(new Error(BAD_REQUEST.value(), "A palavra-passe introduzida não está correta"));
        }

        String jwtToken = jwtService.generateToken(userDetails);
        return ok(new Success(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request, Errors errors) {
        ProblemDetail detail;
        if (errors.hasErrors()) {
            String msg = errors.getAllErrors().stream()
                               .map(ObjectError::getDefaultMessage)
                               .collect(Collectors.joining(". "));
            detail = forStatusAndDetail(BAD_REQUEST, msg);
            return of(detail).build();
        }

        if (userService.usernameExists(request.username())) {
            detail = forStatusAndDetail(BAD_REQUEST, "Este nome de utilizador já existe");
            return of(detail).build();
        } else if (userService.emailExists(request.email())) {
            detail = forStatusAndDetail(BAD_REQUEST, "Este email já existe");
            return of(detail).build();
        }

        userService.register(request);
        return ResponseEntity.status(CREATED).build();
    }

    @PostMapping("/email-check")
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

    @PostMapping("/username-check")
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

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(Principal principal, @RequestBody UpdateRequest request) {
        var result = userService.update(principal.getName(), request);
        if (result == RESOURCE_DOES_NOT_EXISTS) {
            ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, "Erro inesperado ao atualizar perfil");
            return of(detail).build();
        }

        if (result == USERNAME_EXISTS) {
            ProblemDetail detail = forStatusAndDetail(
                    BAD_REQUEST, "O nome de utilizador que tentou actualizar já está existe");
            return of(detail).build();
        }

        return ok().build();
    }

}
