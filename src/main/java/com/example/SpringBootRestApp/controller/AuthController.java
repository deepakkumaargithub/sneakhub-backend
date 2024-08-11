package com.example.SpringBootRestApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootRestApp.config.JwtProvider;
import com.example.SpringBootRestApp.exception.UserException;
import com.example.SpringBootRestApp.model.User;
import com.example.SpringBootRestApp.repository.UserRepository;
import com.example.SpringBootRestApp.request.LoginRequest;
import com.example.SpringBootRestApp.response.AuthResponse;
import com.example.SpringBootRestApp.service.CustomUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUserServiceImplementation customUserService;
    
    public AuthController(UserRepository userRepository,
            CustomUserServiceImplementation customUserService,
            PasswordEncoder passwordEncoder,
            JwtProvider jwtProvider) {
        this.userRepository=userRepository;
        this.customUserService=customUserService;
        this.passwordEncoder=passwordEncoder;
        this.jwtProvider = jwtProvider;
    }
    
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
        
        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        
        User isEmailExist = userRepository.findByEmail(email);
        
        if(isEmailExist != null) {
            throw new UserException("Email is already used with another account");
        }
        
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);
        
        // Save the user
        User savedUser = userRepository.save(createdUser);
        
        // Set the ID of the saved user in the response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwtProvider.generateToken(new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword())));
        authResponse.setMessage("Signup Success");
        authResponse.setId(savedUser.getId());
        
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) {
        
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        
        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Retrieve user ID
        User user = userRepository.findByEmail(userDetails.getUsername());
        Long userId = user.getId();
        
        String token = jwtProvider.generateToken(authentication);
        
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signin Success");
        authResponse.setId(userId); // Set the userId
        
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }


    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserService.loadUserByUsername(username);
        
        if(userDetails == null) {
            throw new BadCredentialsException("Invalid Username");
        }
        
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
    }
}