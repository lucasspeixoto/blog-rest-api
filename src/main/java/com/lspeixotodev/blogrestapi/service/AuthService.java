package com.lspeixotodev.blogrestapi.service;

import com.lspeixotodev.blogrestapi.dto.LoginDTO;
import com.lspeixotodev.blogrestapi.dto.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDto);

    String register(RegisterDTO registerDto);
}