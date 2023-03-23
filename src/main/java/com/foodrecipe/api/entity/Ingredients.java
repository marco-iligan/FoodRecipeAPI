package com.foodrecipe.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;

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
