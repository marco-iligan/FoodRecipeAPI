package com.foodrecipe.api.Service;

import com.foodrecipe.api.Entity.Profile;
import com.foodrecipe.api.Entity.User;
import com.foodrecipe.api.Exception.Exceptions;
import com.foodrecipe.api.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile saveProfile(Profile profile){
        return profileRepository.save(profile);
    }

    public List<Profile> getAllProfiles(){
        return profileRepository.findAll();
    }

    public Profile getProfileById(long profileId){
        return profileRepository.findById(profileId).get();
    }

    public void deleteById(long profileId){
        profileRepository.findById(profileId).orElseThrow(
                () -> new Exceptions("Profile does not exist", new RuntimeException("Bad Request"))
        );
    }

}
