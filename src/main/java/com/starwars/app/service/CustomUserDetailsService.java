package com.starwars.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Value("${swapi.username}")
    private String user;
    @Value("${swapi.password}")
    private String password;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!user.equals(username)) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return User.builder()
                .username(user)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();
    }
}
