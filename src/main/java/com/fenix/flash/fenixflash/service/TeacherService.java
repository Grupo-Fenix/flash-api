package com.fenix.flash.fenixflash.service;

import com.fenix.flash.fenixflash.model.Teacher;
import com.fenix.flash.fenixflash.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public Optional<Teacher> findByUsername(String username) {
        return repository.findByEmail(username);
    }

    public Optional<Teacher> findById(long id) {
        return repository.findById(id);
    }

    public void upsert(Teacher teacher) {
        repository.save(teacher);
    }

    public void delete(Teacher teacher) {
        repository.delete(teacher);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Teacher> findAll() {
        return repository.findAll();
    }
}
