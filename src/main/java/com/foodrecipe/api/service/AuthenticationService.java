package com.foodrecipe.api.service;

import com.foodrecipe.api.auth.AuthenticationRequest;
import com.foodrecipe.api.auth.AuthenticationResponse;
import com.foodrecipe.api.auth.RegisterRequest;
import com.foodrecipe.api.config.JwtService;
import com.foodrecipe.api.entity.Profile;
import com.foodrecipe.api.entity.Role;
import com.foodrecipe.api.entity.User;
import com.foodrecipe.api.repository.ProfileRepository;
import com.foodrecipe.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        Profile profile = Profile.builder()
                .fName(request.getProfile().getFName())
                .lName(request.getProfile().getLName())
                .mName(request.getProfile().getMName())
                .build();
        Profile savedProfile = profileRepository.save(profile);
        User user = User.builder()
                .profile(savedProfile)
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        User savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(savedUser);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
