package com.fenix.flash.fenixflash.model;

import jakarta.persistence.*;

import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "id_curso")
    private Long id;

    @Column(name = "titulo_curso")
    private String titulo;

    @Column(name = "status_curso")
    private String status;

    @Column(name = "concluida_curso")
    private Boolean concluida;

    public Curso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Curso curso)) return false;
        return Objects.equals(id, curso.id) && Objects.equals(titulo, curso.titulo) && Objects.equals(status, curso.status) && Objects.equals(concluida, curso.concluida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, status, concluida);
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", status='" + status + '\'' +
                ", concluida=" + concluida +
                '}';
    }

}
