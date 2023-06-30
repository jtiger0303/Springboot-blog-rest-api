package com.example.springbootblogrestapi.service.impl;

import com.example.springbootblogrestapi.entity.Role;
import com.example.springbootblogrestapi.entity.User;
import com.example.springbootblogrestapi.exception.BlogAPIException;
import com.example.springbootblogrestapi.payload.LoginDto;
import com.example.springbootblogrestapi.payload.SignUpDto;
import com.example.springbootblogrestapi.repository.RoleRepository;
import com.example.springbootblogrestapi.repository.UserRepository;
import com.example.springbootblogrestapi.security.JwtTokenProvider;
import com.example.springbootblogrestapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider){
        this.authenticationManager=authenticationManager;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtTokenProvider=jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(SignUpDto signUpDto) {
        //add check for username exists in database
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }
        User user= new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Set<Role> roles= new HashSet<>();
        Role userRole=roleRepository.findByName("ROLE_USER").get(); //give the role_user for default user
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully!";
    }
}
