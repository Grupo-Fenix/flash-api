package com.fenix.flash.fenixflash.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "aluno")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluno_gen")
    @SequenceGenerator(name = "aluno_gen", sequenceName = "aluno_id_seq", allocationSize = 1)
    @Column(name = "id_aluno")
    private Long id;

    @Column(name = "nome_aluno", nullable = false)
    private String name;

    @Column(name = "sobrenome_aluno", nullable = false)
    private String surname;

    @Column(name = "email_aluno", nullable = false, unique = true)
    private String email;

    @Column(name = "senha_aluno", nullable = false)
    private String password;

    public Student() {
    }

    //<editor-fold desc="Getters and setters">
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //</editor-fold>

    //<editor-fold desc="Equals, HashCode, and ToString">
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Student student)) return false;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(surname, student.surname) && Objects.equals(email, student.email) && Objects.equals(password, student.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    //</editor-fold>

}
