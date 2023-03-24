package com.foodrecipe.api.Controller;

import com.foodrecipe.api.Entity.User;
import com.foodrecipe.api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable(value = "userId")long userId){
        try{
            User user= userService.getUserById(userId);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(value = "/delete/{userId}")
    public void deleteUser(@PathVariable(value = "userId")Long userId){
        userService.deleteUser(userId);
    }

}
