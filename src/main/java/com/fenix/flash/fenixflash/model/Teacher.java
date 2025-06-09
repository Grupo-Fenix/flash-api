package com.fenix.flash.fenixflash.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "professor")
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prof_id_gen")
    @SequenceGenerator(name = "prof_id_gen", sequenceName = "professor_id_seq", allocationSize = 1)
    @Column(name = "id_prof")
    private Long id;

    @Column(name = "nome_prof")
    private String name;

    @Column(name = "sobrenome_prof")
    private String surname;

    @Column(name = "data_nascimento_prof")
    private LocalDate birthdate;

    @Column(name = "email_prof")
    private String email;

    @Column(name = "telefone_prof")
    private String phone;

    @Column(name = "nacionalidade_prof")
    private String nationality;

    @Column(name = "grau_academico_prof")
    private String academicDegree;

    @Column(name = "area_de_formacao_prof")
    private String fieldOfStudy;

    @Column(name = "instituicao_prof")
    private String institution;

    @Column(name = "ano_de_formacao_prof")
    private Integer graduationYear;

    @Column(name = "ja_ensinou_online_prof")
    private Boolean hasTaughtOnline;

    @Column(name = "descricao_experiencia_prof")
    private String experienceDescription;

    @Column(name = "url_curriculum_prof")
    private String cvFile;

    @Column(name = "url_identificacao_prof")
    private String idFile;

    @Column(name = "url_video_apresentacao_prof")
    private String videoFile;

    @Column(name = "rede_social_prof")
    private String socialMedia;

    public Teacher() {
    }

    //<editor-fold desc="Getters and Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Boolean getHasTaughtOnline() {
        return hasTaughtOnline;
    }

    public void setHasTaughtOnline(Boolean hasTaughtOnline) {
        this.hasTaughtOnline = hasTaughtOnline;
    }

    public String getExperienceDescription() {
        return experienceDescription;
    }

    public void setExperienceDescription(String experienceDescription) {
        this.experienceDescription = experienceDescription;
    }

    public String getCvFile() {
        return cvFile;
    }

    public void setCvFile(String cvFile) {
        this.cvFile = cvFile;
    }

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }
    //</editor-fold>

    //<editor-fold desc="Equals, HashCode, and ToString">
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Teacher teacher)) return false;
        return Objects.equals(id, teacher.id) && Objects.equals(name, teacher.name) && Objects.equals(surname, teacher.surname) && Objects.equals(birthdate, teacher.birthdate) && Objects.equals(email, teacher.email) && Objects.equals(phone, teacher.phone) && Objects.equals(nationality, teacher.nationality) && Objects.equals(academicDegree, teacher.academicDegree) && Objects.equals(fieldOfStudy, teacher.fieldOfStudy) && Objects.equals(institution, teacher.institution) && Objects.equals(graduationYear, teacher.graduationYear) && Objects.equals(hasTaughtOnline, teacher.hasTaughtOnline) && Objects.equals(experienceDescription, teacher.experienceDescription) && Objects.equals(cvFile, teacher.cvFile) && Objects.equals(idFile, teacher.idFile) && Objects.equals(videoFile, teacher.videoFile) && Objects.equals(socialMedia, teacher.socialMedia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthdate, email, phone, nationality, academicDegree, fieldOfStudy, institution, graduationYear, hasTaughtOnline, experienceDescription, cvFile, idFile, videoFile, socialMedia);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", nationality='" + nationality + '\'' +
                ", academicDegree='" + academicDegree + '\'' +
                ", fieldOfStudy='" + fieldOfStudy + '\'' +
                ", institution='" + institution + '\'' +
                ", graduationYear=" + graduationYear +
                ", hasTaughtOnline=" + hasTaughtOnline +
                ", experienceDescription='" + experienceDescription + '\'' +
                ", cvFile='" + cvFile + '\'' +
                ", idFile='" + idFile + '\'' +
                ", videoFile='" + videoFile + '\'' +
                ", socialMedia='" + socialMedia + '\'' +
                '}';
    }
    //</editor-fold>

}
