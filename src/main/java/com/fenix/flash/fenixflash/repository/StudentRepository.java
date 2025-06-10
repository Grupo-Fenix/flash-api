package com.fenix.flash.fenixflash.repository;

import com.fenix.flash.fenixflash.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    @Query("select (count(s) > 0) from Student s where upper(s.email) = upper(?1)")
    boolean existsByEmailIgnoreCase(String email);

}
