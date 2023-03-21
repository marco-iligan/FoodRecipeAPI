package com.foodrecipe.api.repository;

import com.foodrecipe.api.entity.Profile;
import com.foodrecipe.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByProfile(Profile profile);
}
