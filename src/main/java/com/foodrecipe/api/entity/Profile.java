package com.foodrecipe.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="profile_tbl")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name="userId",
            updatable = false
    )
    private Long userId;

    @Column(
            name="firstname",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private String fName;

    @Column(
            name = "lastname",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private String lName;

    @Column(
            name="midname",
            columnDefinition = "varchar(50)"
    )
    private String mName;

}
