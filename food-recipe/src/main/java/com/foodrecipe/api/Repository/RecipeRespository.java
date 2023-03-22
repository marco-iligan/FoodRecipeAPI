package com.foodrecipe.api.Repository;

import com.foodrecipe.api.Entity.Category;
import com.foodrecipe.api.Entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRespository extends JpaRepository<Recipe, Long> {

}
