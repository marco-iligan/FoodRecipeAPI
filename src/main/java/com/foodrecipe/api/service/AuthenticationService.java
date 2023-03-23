package com.foodrecipe.api.service;

import com.foodrecipe.api.auth.AuthenticationRequest;
import com.foodrecipe.api.auth.AuthenticationResponse;
import com.foodrecipe.api.auth.RegisterRequest;
import com.foodrecipe.api.config.JwtService;
import com.foodrecipe.api.entity.Profile;
import com.foodrecipe.api.entity.Role;
import com.foodrecipe.api.entity.User;
import com.foodrecipe.api.exception.ApiRequestException;
import com.foodrecipe.api.repository.ProfileRepository;
import com.foodrecipe.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        boolean flag = userRepository.existsByUsername(request.getUsername());
        if(!flag){
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
        throw new ApiRequestException("Username already exists.");
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return new AuthenticationResponse(jwtToken);
        }catch (Exception e){
            throw new ApiRequestException("Invalid username or password.");
        }
    }
}
