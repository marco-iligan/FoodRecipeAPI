package com.foodrecipe.api.Service;

import com.foodrecipe.api.Entity.User;
import com.foodrecipe.api.Exception.Exceptions;
import com.foodrecipe.api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileService profileService;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(long userId){
        return userRepository.findById(userId).get();
    }

    public void deleteUser(long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new Exceptions("User does not exist", new RuntimeException("Bad Request"))
        );
        profileService.deleteById(user.getProfile().getProfileId());
        userRepository.deleteById(userId);
    }
}
