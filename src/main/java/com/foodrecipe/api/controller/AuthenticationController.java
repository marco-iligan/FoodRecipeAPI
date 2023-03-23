package com.foodrecipe.api.controller;

import com.foodrecipe.api.auth.AuthenticationRequest;
import com.foodrecipe.api.auth.AuthenticationResponse;
import com.foodrecipe.api.auth.RegisterRequest;
import com.foodrecipe.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping(path="/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @GetMapping(path="/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
