package com.foodrecipe.api.auth;

import com.foodrecipe.api.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Profile profile;
    private String username;
    private String password;
}
