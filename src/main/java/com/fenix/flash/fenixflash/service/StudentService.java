package com.fenix.flash.fenixflash.service;

import com.fenix.flash.fenixflash.dto.StudentRegistration;
import com.fenix.flash.fenixflash.model.Student;
import com.fenix.flash.fenixflash.repository.StudentRepository;
import com.fenix.flash.fenixflash.repository.TeacherRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final PasswordEncoder encoder;
    private final StudentRegistration.Mapper mapper;

    public StudentService(StudentRepository repository, PasswordEncoder encoder, StudentRegistration.Mapper mapper) {
        this.repository = repository;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    public void upsert(StudentRegistration registration) {
        Student student = mapper.apply(registration);
        student.setPassword(encoder.encode(student.getPassword()));
        repository.save(student);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public boolean exists(String email) {
        return repository.existsByEmailIgnoreCase(email);
    }

}
