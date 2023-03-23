package com.foodrecipe.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="recipe_tbl")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name="id",
            updatable = false
    )
    private Long Id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="profile_tbl_userId", referencedColumnName = "userId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Profile profile;

    @Column(
            name="title",
            columnDefinition = "varchar(50)"
    )
    private String title;

    @Column(
            name="description",
            columnDefinition = "TEXT"
    )
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name="recipe_ingredients_tbl",
    joinColumns = {
            @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "ingredients_id", referencedColumnName = "id")
    })
    private Set<Ingredients> ingredients;
}
