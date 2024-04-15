package com.bobtime.repository;

import com.bobtime.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    long deleteByNumBetween(Long numStart, Long numEnd);

    Optional<User> findByName(String name);
}
