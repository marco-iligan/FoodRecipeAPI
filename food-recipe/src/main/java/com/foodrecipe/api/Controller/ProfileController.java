package com.foodrecipe.api.Controller;

import com.foodrecipe.api.Entity.Profile;
import com.foodrecipe.api.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/user/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping(value = "/all")
    public List<Profile> getAllProfiles(){
        return profileService.getAllProfiles();
    }

    @GetMapping(value = "/{profileId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable(value = "profileId") long profileId){
        try{
            Profile profile = profileService.getProfileById(profileId);
            return new ResponseEntity<Profile>(profile, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Profile>(HttpStatus.NOT_FOUND);
        }

    }

}
