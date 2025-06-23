package com.fenix.flash.fenixflash.service;

import com.fenix.flash.fenixflash.auth.RegisterRequest;
import com.fenix.flash.fenixflash.auth.UpdateRequest;
import com.fenix.flash.fenixflash.dto.PageResponse;
import com.fenix.flash.fenixflash.dto.UserDto;
import com.fenix.flash.fenixflash.model.User;
import com.fenix.flash.fenixflash.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.fenix.flash.fenixflash.util.FlashConstants.*;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserDto.Mapper mapper;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, UserDto.Mapper mapper, PasswordEncoder encoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    public PageResponse<UserDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDto> userPage = repository.findAllEnabled(pageable).map(mapper);
        return PageResponse.create(userPage);
    }

    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    public Optional<UserDto> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper);
    }

    public Optional<UserDto> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper);
    }

    public boolean emailExists(String email) {
        return repository.existsByEmailIgnoreCase(email);
    }

    public boolean usernameExists(String username) {
        return repository.existsByUsernameIgnoreCase(username);
    }

    public void register(RegisterRequest request) {
        User user = request.toUser();
        user.setPassword(encoder.encode(request.senha()));
        repository.save(user);
    }

    public int update(String username, UpdateRequest request) {
        if (repository.existsByUsernameIgnoreCase(request.username())) {
            return USERNAME_EXISTS;
        }

        Optional<User> optional = repository.findByUsername(username)
                                            .or(() -> repository.findByEmail(username));
        if (optional.isEmpty()) {
            return RESOURCE_DOES_NOT_EXISTS;
        }
        User user = optional.get();

        if (request.username() != null && !request.username().isBlank()) {
            user.setUsername(request.username());
        }

        if (request.senha() != null && !request.senha().isBlank()) {
            user.setPassword(encoder.encode(request.senha()));
        }

        repository.save(user);
        return SUCCESS;
    }

    public int update(User user) {
        if (user.getId() == null) {
            return RESOURCE_DOES_NOT_EXISTS;
        }

        Optional<User> optional = findById(user.getId());
        if (optional.isEmpty()) {
            return RESOURCE_DOES_NOT_EXISTS;
        }

        if (!optional.get().getType().equals(user.getType())) {
            return UNSUPPORTED_OPERATION;
        }

        if (usernameExists(user.getUsername())) {
            return USERNAME_EXISTS;
        }
        if (emailExists(user.getEmail())) {
            return EMAIL_EXISTS;
        }
        repository.save(user);
        return SUCCESS;
    }

    public void delete(User user) {
        if (repository.exists(Example.of(user))) {
            user.setDeleted(true);
            repository.save(user);
        }
    }

}
