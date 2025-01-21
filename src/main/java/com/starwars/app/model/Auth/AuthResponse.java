package com.starwars.app.model.Auth;

public class AuthResponse {

    private String token;

    public AuthResponse() {
        //No-Args Constructor
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
