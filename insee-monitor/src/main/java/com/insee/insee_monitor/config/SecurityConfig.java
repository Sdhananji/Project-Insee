package com.insee.insee_monitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // disable CSRF for APIs (Postman etc.)
            .cors(cors ->{}) //enables CORS support in spring security
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/workers/reg","/api/auth/login","/api/bins/**").permitAll() // allow registration without auth
                .anyRequest().authenticated() // other endpoints require login
            )
        
            .formLogin(login -> login.disable())
            .httpBasic(basic -> basic.disable()); // no basic auth popup

        return http.build();
    }

    //CORS configuration
    @Bean
    CorsConfigurationSource CorsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);

        return source;
    }
}
