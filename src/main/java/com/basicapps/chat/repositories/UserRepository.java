package com.basicapps.chat.repositories;

import com.basicapps.chat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUserInfoEmail(String email);
    Optional<User> findById(Long id);
}
