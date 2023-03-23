package com.foodrecipe.api.controller;

import com.foodrecipe.api.entity.Profile;
import com.foodrecipe.api.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/v1/profile")
public class ProfileController {
    private final ProfileService profileService;

    @PutMapping(path="/update-profile")
    public @ResponseBody Profile updateProfile(@RequestBody Profile request){
        return profileService.updateProfile(request);
    }
}
