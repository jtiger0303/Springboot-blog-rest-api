package com.example.springbootblogrestapi.service;

import com.example.springbootblogrestapi.payload.LoginDto;
import com.example.springbootblogrestapi.payload.SignUpDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(SignUpDto signUpDto);
}
