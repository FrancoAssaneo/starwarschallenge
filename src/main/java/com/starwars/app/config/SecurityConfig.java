package com.starwars.app.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class that sets up the security policies for the application.
 * This class extends {@link WebSecurityConfigurerAdapter} to configure the authentication,
 * authorization, and security filters for the application. It enables JWT-based authentication
 * and secures the endpoints based on roles and access control.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a new {@link SecurityConfig} with the provided dependencies.
     *
     * @param jwtAuthenticationFilter the custom JWT authentication filter
     * @param userDetailsService the custom user details service used for authentication
     * @param passwordEncoder the password encoder used for encoding passwords
     */
    public SecurityConfig(@Lazy JwtAuthenticationFilter jwtAuthenticationFilter,
                          @Lazy UserDetailsService userDetailsService,
                          @Lazy PasswordEncoder passwordEncoder) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Exposes the {@link AuthenticationManager} as a bean for use in other parts of the application.
     *
     * @return the authentication manager
     * @throws Exception if an error occurs during bean creation
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Provides a {@link PasswordEncoder} bean for password encoding. This method uses
     * {@link BCryptPasswordEncoder} as the password encoder.
     *
     * @return a {@link PasswordEncoder} instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the {@link AuthenticationManagerBuilder} for setting up custom user details service
     * and password encoder for authentication.
     *
     * @param auth the {@link AuthenticationManagerBuilder} to configure
     * @throws Exception if an error occurs during configuration
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * Configures the {@link HttpSecurity} for securing the application endpoints.
     * This method configures:
     *   Disabling CSRF (Cross-Site Request Forgery) protection for stateless API
     *   Setting the session creation policy to {@link SessionCreationPolicy#STATELESS}
     *   Configuring authorization rules for various endpoints, allowing public access to login
     *   Adding the {@link JwtAuthenticationFilter} before the {@link UsernamePasswordAuthenticationFilter}
     *
     * @param http the {@link HttpSecurity} to configure
     * @throws Exception if an error occurs during configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers(HttpMethod.GET, "/people/**", "/films/**", "/starships/**", "/vehicles/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

