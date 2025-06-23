package com.fenix.flash.fenixflash.auth;

import jakarta.validation.constraints.NotNull;

public record UpdateRequest(
        @NotNull
        Integer id,
        String username,
        String email,
        String senha
) {
}
