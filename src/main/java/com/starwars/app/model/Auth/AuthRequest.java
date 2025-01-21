package com.starwars.app.model.Auth;

public class AuthRequest {

    private String username;
    private String password;

    public AuthRequest() {
        //No-Args Constructor
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
