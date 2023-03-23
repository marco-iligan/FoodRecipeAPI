package com.foodrecipe.api.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private long recipeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_profile_id", referencedColumnName = "profile_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Profile profile;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    private List<String> ingredients;
}
