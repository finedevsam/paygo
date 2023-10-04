package com.paygo.paygo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paygo.paygo.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    User findUserByEmail(String email);
}
