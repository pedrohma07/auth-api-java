package com.example.authapijava.repositories;

import com.example.authapijava.model.user.User;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Nonnull
    Optional<User> findById(@Nonnull String id);

    @Nonnull
    User findByEmail(@Nonnull String email);

}
