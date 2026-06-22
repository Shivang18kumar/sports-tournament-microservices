package com.shivang.auth_service.controller;

import com.shivang.auth_service.dto.LoginRequest;
import com.shivang.auth_service.dto.RegisterRequest;
import com.shivang.auth_service.security.JwtService;
import com.shivang.auth_service.service.AuthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        System.out.println("REGISTER REQUEST RECEIVED");

        return authService.register(request);
    }
    @GetMapping("/test")
    public String test(){
        return "controller working";
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @GetMapping("/profile")
    public String profile() {
        return "Welcome Authenticated User";
    }

    @GetMapping("/me")
    public String me(Authentication authentication) {

        return authentication.getName();
    }

    @GetMapping("/role")
    public String role(
            Authentication authentication,
            @RequestHeader("Authorization")
            String authHeader) {

        String token =
                authHeader.substring(7);

        return jwtService.extractRole(token);
    }

    @PreAuthorize("hasRole('ORGANIZER')")
    @GetMapping("/organizer")
    public String organizerOnly() {
        return "Welcome Organizer";
    }


}