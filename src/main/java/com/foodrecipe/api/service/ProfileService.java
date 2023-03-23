package com.foodrecipe.api.service;

import com.foodrecipe.api.entity.Profile;
import com.foodrecipe.api.exception.ApiRequestException;
import com.foodrecipe.api.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public Profile updateProfile(Profile request){
        Profile profile = profileRepository.findById(request.getUserId()).orElseThrow(
                () -> new ApiRequestException("Ingredient doesn't exists."));
        profile.setFName(request.getFName());
        profile.setLName(request.getLName());
        profile.setMName(request.getMName());

        return profileRepository.save(profile);
    }

}
