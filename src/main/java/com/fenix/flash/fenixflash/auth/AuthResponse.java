package com.fenix.flash.fenixflash.auth;

public sealed interface AuthResponse {
    record Success(String token) implements AuthResponse {
    }

    record Error(int code, String reason) implements AuthResponse {
    }
}
