package com.fenix.flash.fenixflash.auth;

import com.fenix.flash.fenixflash.model.User;
import com.fenix.flash.fenixflash.model.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static jakarta.validation.constraints.Pattern.Flag.CASE_INSENSITIVE;

public record RegisterRequest(
        @NotBlank(message = "O nome de utilizador é obrigatório")
        @Size(max = 15, message = "O nome de utilizador deve conter apenas 15 caracteres")
        String username,

        @NotBlank(message = "O email é obrigatório")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha,

        @NotNull(message = "O tipo de utilizador é obrigatório")
        @Pattern(regexp = "(ADMINISTRADOR|ALUNO|GERENTE|PROFESSOR)", message = "Tipo de utilizador inválido", flags = {CASE_INSENSITIVE})
        String tipo
) {
    //<editor-fold desc="Mapper function">
    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(senha);
        user.setType(UserType.valueOf(tipo.toUpperCase()));
        user.setDeleted(false);
        return user;
    }
    //</editor-fold>
}
