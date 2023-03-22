package com.foodrecipe.api.service;

import com.foodrecipe.api.entity.Profile;
import com.foodrecipe.api.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public Profile updateProfile(Profile request){
        Profile profile = profileRepository.findById(request.getUserId()).orElseThrow();
        if(profile != null){
            profile.setFName(request.getFName());
            profile.setLName(request.getLName());
            profile.setMName(request.getMName());

            profileRepository.save(profile);
            return profile;
        }
        return profile;
    }

}
