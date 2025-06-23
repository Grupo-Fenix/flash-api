package com.fenix.flash.auth;

public record EmailUsernameVerification(
        boolean exists,
        String msg
) {

}
