package com.fenix.flash.fenixflash.auth;

public record EmailUsernameVerification(
        boolean exists,
        String msg
) {

}
