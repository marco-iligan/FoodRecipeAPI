package com.foodrecipe.api.repository;

import com.foodrecipe.api.entity.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {

}
