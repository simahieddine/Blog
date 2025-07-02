package com.maison.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())       // ⛔ CSRF protection disabled
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()    // ✅ All endpoints are open
                )
                .formLogin(form -> form.disable())                // ⛔ Disable form-based login
                .httpBasic(basic -> basic.disable());             // ⛔ Disable HTTP Basic login

        return http.build();
    }
}


