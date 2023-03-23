package com.foodrecipe.api.Controller;

import com.foodrecipe.api.Entity.Profile;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private Profile profile;

}
