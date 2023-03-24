package com.foodrecipe.api.Repository;

import com.foodrecipe.api.Entity.Profile;
import com.foodrecipe.api.Entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRespository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByProfile(Profile profile);

    @Query(
            value = "SELECT * FROM recipe r WHERE r.recipe_profile_id = :profileId AND r.recipe_id = :recipeId",
            nativeQuery = true)
    List <Recipe> findByIdAndProfile(@Param("profileId") long profileID,@Param("recipeId") long recipeId);

    @Query(
            value = "SELECT * FROM recipe r WHERE r.recipe_profile_id = :profileId AND r.title LIKE %:title%",
            nativeQuery = true)
    List <Recipe> findByTitle(@Param("profileId") long profileID,@Param("title") String title);

    @Query(
            value = "SELECT * FROM recipe r WHERE r.recipe_profile_id = :profileId AND r.category LIKE :category",
            nativeQuery = true)
    List <Recipe> findByCategory(@Param("profileId") long profileID,@Param("category") String category);

    @Query(
            value = "SELECT * FROM recipe r WHERE r.recipe_profile_id = :profileId AND  r.ingredients LIKE %:ingredient%",
            nativeQuery = true)
    List <Recipe> findByIngredient(@Param("profileId") long profileID,@Param("ingredient") String ingredient);

}
