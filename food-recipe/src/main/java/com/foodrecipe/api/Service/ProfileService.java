package com.foodrecipe.api.Service;

import com.foodrecipe.api.Entity.Profile;
import com.foodrecipe.api.Exception.Exceptions;
import com.foodrecipe.api.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile updateProfile(Profile profile){
        profileRepository.findById(profile.getProfileId()).orElseThrow(
                () -> new Exceptions("Profile does not exist", new RuntimeException("Bad Request"))
        );
        return profileRepository.save(profile);
    }

    public void deleteProfile(long profileId){
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                () -> new Exceptions("User does not exist", new RuntimeException("Bad Request"))
        );
        profileRepository.deleteById(profileId);
    }

}
