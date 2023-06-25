package com.lspeixotodev.blogrestapi.controller;

import com.lspeixotodev.blogrestapi.dto.JWTAuthResponse;
import com.lspeixotodev.blogrestapi.dto.LoginDTO;
import com.lspeixotodev.blogrestapi.dto.RegisterDTO;
import com.lspeixotodev.blogrestapi.service.AuthService;
import com.lspeixotodev.blogrestapi.utils.MediaType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON)
public class AuthController {

    @Autowired
    private AuthService authService;

    // Login Rest API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDto) {
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Register Rest API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDto) {
        String response = authService.register(registerDto);

        return ResponseEntity.ok(response);
    }


}
