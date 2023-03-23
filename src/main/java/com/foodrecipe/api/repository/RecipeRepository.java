package com.foodrecipe.api.repository;

import com.foodrecipe.api.entity.Category;
import com.foodrecipe.api.entity.Profile;
import com.foodrecipe.api.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByProfile(Profile profile);
    @Query(value="select distinct r1_0, p1_0 from Recipe r1_0 join Profile p1_0 join Ingredients i1_1 where p1_0.userId=?1 and concat(r1_0.title,r1_0.description,r1_0.category, i1_1.name,i1_1.description) like %?2%")
    List<Recipe> search(Long userId,String keyword);

    Recipe findByProfileAndId(Profile profile, long id);
    
}
