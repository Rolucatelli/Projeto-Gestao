package com.example.bluevelvetmusicstore.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf()
                .ignoringRequestMatchers("/h2-console/**") 
                .disable()
            .authorizeHttpRequests()
                .requestMatchers("/api/user/v1/create").permitAll() 
                .requestMatchers("/h2-console/**").permitAll() 
                .anyRequest().authenticated() 
            .and()
            .headers()
                .frameOptions().sameOrigin() 
            .and()
            .httpBasic(); 
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }
}
