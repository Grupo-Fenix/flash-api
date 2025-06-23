package com.fenix.flash.fenixflash.auth;

public record LoginRequest(
        String email,
        String password
) {
}
