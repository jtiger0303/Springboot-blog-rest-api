package com.example.springbootblogrestapi.controller;

import com.example.springbootblogrestapi.entity.Role;
import com.example.springbootblogrestapi.entity.User;
import com.example.springbootblogrestapi.payload.JWTAuthResponse;
import com.example.springbootblogrestapi.payload.LoginDto;
import com.example.springbootblogrestapi.payload.SignUpDto;
import com.example.springbootblogrestapi.repository.RoleRepository;
import com.example.springbootblogrestapi.repository.UserRepository;
import com.example.springbootblogrestapi.security.JwtTokenProvider;
import com.example.springbootblogrestapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public AuthController(AuthService authService){
        this.authService=authService;
    }

    //Build Login REST API
    @PostMapping(value={"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token=authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse=new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }
//    @PostMapping("/signin")
//    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
//       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication); //인증

        //get token from tokenProvider
//        String token=tokenProvider.generateToken(authentication);
//        return ResponseEntity.ok(new JWTAuthResponse(token));
//    }

    //Build Register REST API
    @PostMapping(value={"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody SignUpDto signUpDto){
       String response=authService.register(signUpDto);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
