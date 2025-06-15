package com.fenix.flash.fenixflash.dto;

import jakarta.validation.constraints.NotNull;

public record UserUpdateRequest(
        @NotNull
        Integer id,
        String username,
        String email,
        String senha
) {
}
