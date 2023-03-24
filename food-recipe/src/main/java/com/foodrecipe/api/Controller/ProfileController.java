package com.foodrecipe.api.Controller;

import com.foodrecipe.api.Entity.Profile;
import com.foodrecipe.api.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PutMapping("/update")
    public Profile updteProfile(@RequestBody Profile profile){
        return profileService.updateProfile(profile);
    }

}
