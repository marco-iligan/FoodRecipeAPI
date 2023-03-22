package com.foodrecipe.api.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private long profileId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "mid_name")
    private String midName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Recipe> recipe;

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }
}
