package com.insutek.server.repository;

import com.insutek.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface InsutekUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
