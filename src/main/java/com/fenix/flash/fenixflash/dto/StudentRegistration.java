package com.fenix.flash.fenixflash.dto;

import com.fenix.flash.fenixflash.model.Student;
import com.fenix.flash.fenixflash.model.Teacher;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.util.function.Function;

public record StudentRegistration(
        @NotBlank(message = "O nome é obrigatório")
        String firstName,
        @NotBlank(message = "O sobrenome é obrigatório")
        String lastName,
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
        String password
) {
    @Component
    public static final class Mapper implements Function<StudentRegistration, Student> {
        @Override
        public Student apply(StudentRegistration req) {
            Student student = new Student();
            student.setName(req.firstName);
            student.setSurname(req.lastName);
            student.setEmail(req.email);
            student.setPassword(req.password);
            return student;
        }
    }
}
