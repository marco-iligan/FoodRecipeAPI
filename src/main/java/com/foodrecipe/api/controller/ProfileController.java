package com.foodrecipe.api.controller;

import com.foodrecipe.api.entity.Profile;
import com.foodrecipe.api.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PutMapping(path="/update-profile")
    public @ResponseBody Profile updateProfile(@RequestBody Profile request){
        return profileService.updateProfile(request);
    }
}
