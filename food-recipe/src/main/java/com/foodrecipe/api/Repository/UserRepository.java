package com.foodrecipe.api.Repository;

import com.foodrecipe.api.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    public Optional<User> findByUsername(String username);
}
