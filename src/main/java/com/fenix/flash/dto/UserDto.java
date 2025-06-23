package com.fenix.flash.dto;

import com.fenix.flash.model.User;
import com.fenix.flash.model.UserType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;

public record UserDto(
        String username,
        String email,
        UserType type,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    @Component
    public static final class Mapper implements Function<User, UserDto> {
        @Override
        public UserDto apply(User user) {
            return new UserDto(
                    user.getUsername(),
                    user.getEmail(),
                    user.getType(),
                    user.getCreatedAt(),
                    user.getUpdatedAt()
            );
        }
    }
}
