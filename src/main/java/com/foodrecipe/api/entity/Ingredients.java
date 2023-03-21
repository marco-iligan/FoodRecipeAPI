package com.foodrecipe.api.entity;

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
@Table(name="ingredients_tbl")
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name="id",
            updatable = false
    )
    private Integer id;

    @Column(
            name="name",
            columnDefinition = "varchar(50)"
    )
    private String name;

    @Column(
            name="description",
            columnDefinition = "TEXT"
    )
    private String description;
}