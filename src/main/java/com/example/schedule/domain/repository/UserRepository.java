package com.example.schedule.domain.repository;

import com.example.schedule.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 유저 DB 접근을 위한 JPA Repository
 */

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
