package com.fenix.flash.repository;

import com.fenix.flash.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where not u.deleted")
    public List<User> findAllNotDeleted();

    @Query("select u from User u where not u.deleted")
    public Page<User> findAllEnabled(Pageable pageable);

    @Query("select u from User u where u.username = :username and not u.deleted")
    Optional<User> findByUsername(String username);

    @Query("select u from User u where u.email = :email and not u.deleted")
    Optional<User> findByEmail(String email);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUsernameIgnoreCase(String username);
}
