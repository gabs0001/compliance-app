package com.esg.compliance.api.controller;

import com.esg.compliance.api.dto.login.LoginRequest;
import com.esg.compliance.api.dto.login.LoginResponse;
import com.esg.compliance.api.dto.register.RegisterRequest;
import com.esg.compliance.domain.service.AuthService;
import com.esg.compliance.security.CustomUserDetailsService;
import com.esg.compliance.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request.username(), request.password());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        var userDetails = userDetailsService.loadUserByUsername(request.username());

        if(!passwordEncoder.matches(request.password(), userDetails.getPassword())) {
            throw new UsernameNotFoundException("Invalid credentials");
        }

        String token = jwtService.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new LoginResponse(token));
    }
}