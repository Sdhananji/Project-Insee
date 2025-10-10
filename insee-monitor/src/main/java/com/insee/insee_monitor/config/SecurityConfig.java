package com.insee.insee_monitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // disable CSRF for APIs (Postman etc.)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/workers/reg").permitAll() // allow registration without auth
                .anyRequest().authenticated() // other endpoints require login
            )
            .formLogin(login -> login.disable())
            .httpBasic(basic -> basic.disable()); // no basic auth popup

        return http.build();
    }
}
