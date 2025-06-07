package com.fenix.flash.fenixflash.auth;

public record AuthenticationRequest(
        String email,
        String password
) {
}
