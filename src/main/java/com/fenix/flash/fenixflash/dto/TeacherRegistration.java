package com.fenix.flash.fenixflash.dto;

import com.fenix.flash.fenixflash.model.Teacher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.function.Function;

public record TeacherRegistration(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email,
        @NotBlank String phone,
        @Past LocalDate birthDate,
        @NotBlank String nationality,
        @NotBlank String academicDegree,
        @NotBlank String fieldOfStudy,
        @NotBlank String institution,
        Integer graduationYear,
        Integer yearsOfExperience,
        Boolean hasTaughtOnline,
        String experienceDescription,
        @NotNull MultipartFile cvFile,
        @NotNull MultipartFile idFile,
        @NotNull MultipartFile certificateFiles,
        @NotNull MultipartFile videoFile,
        @NotBlank String socialMediaLinks
) {
    @Component
    public static class Mapper implements Function<TeacherRegistration, Teacher> {
        @Override
        public Teacher apply(TeacherRegistration req) {
            Teacher teacher = new Teacher();
            teacher.setName(req.firstName);
            teacher.setSurname(req.lastName);
            teacher.setBirthdate(req.birthDate);
            teacher.setEmail(req.email);
            teacher.setPhone(req.phone);
            teacher.setNationality(req.nationality);
            teacher.setAcademicDegree(req.academicDegree);
            teacher.setFieldOfStudy(req.fieldOfStudy);
            teacher.setGraduationYear(req.graduationYear);
            teacher.setInstitution(req.institution);
            teacher.setHasTaughtOnline(req.hasTaughtOnline);
            teacher.setExperienceDescription(req.experienceDescription);
            teacher.setSocialMedia(req.socialMediaLinks);
            return teacher;
        }
    }

}
