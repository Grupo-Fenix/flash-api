package com.fenix.flash.auth;

public record LoginRequest(
        String email,
        String password
) {
}
