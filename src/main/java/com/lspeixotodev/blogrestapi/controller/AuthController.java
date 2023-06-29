package com.lspeixotodev.blogrestapi.controller;

import com.lspeixotodev.blogrestapi.dto.JWTAuthResponse;
import com.lspeixotodev.blogrestapi.dto.LoginDTO;
import com.lspeixotodev.blogrestapi.dto.RegisterDTO;
import com.lspeixotodev.blogrestapi.service.AuthService;
import com.lspeixotodev.blogrestapi.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON)
@Tag(
        name = "CRUD REST APIs for Authentication Resource"
)
public class AuthController {

    @Autowired
    private AuthService authService;

    // Login Rest API
    @Operation(
            summary = "Login into Blog system",
            description = "Login is used to authenticate an user checking the credentials"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDto) {
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Register Rest API
    @Operation(
            summary = "Register into Blog system",
            description = "Register is used to create a new user in the Blog system"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDto) {
        String response = authService.register(registerDto);

        return ResponseEntity.ok(response);
    }


}
