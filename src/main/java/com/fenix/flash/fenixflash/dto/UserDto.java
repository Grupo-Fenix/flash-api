package com.fenix.flash.fenixflash.dto;

import com.fenix.flash.fenixflash.model.User;
import com.fenix.flash.fenixflash.model.UserType;
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
