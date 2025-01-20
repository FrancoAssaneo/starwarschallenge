package com.starwars.app.controller;

import com.starwars.app.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for handling authentication requests.
 * This controller provides an endpoint for logging in users by generating a JWT token
 * based on the provided username. The token is returned in the response to authenticate
 * the user for subsequent API requests.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    /**
     * Constructs a new {@link AuthController} with the specified {@link JwtUtil} instance.
     *
     * @param jwtUtil the utility class responsible for generating JWT tokens
     */
    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Logs in a user by generating a JWT token based on the provided username.
     * This method receives the username from the request, generates a JWT token using the
     * {@link JwtUtil} class, and returns the token in a response map.
     *
     * @param username the username of the user attempting to log in
     * @return a {@link ResponseEntity} containing a map with the JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username) {
        String token = jwtUtil.generateToken(username);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}