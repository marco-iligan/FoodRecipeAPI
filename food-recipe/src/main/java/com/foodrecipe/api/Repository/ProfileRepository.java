package com.foodrecipe.api.Repository;

import com.foodrecipe.api.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
