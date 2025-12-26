package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 🔴 REQUIRED for Swagger in browser
            .cors(Customizer.withDefaults())

            // 🔴 CSRF off for stateless APIs
            .csrf(csrf -> csrf.disable())

            // 🔴 Stateless JWT-style API
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth
                // 🔴 MUST allow preflight
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // 🔴 Swagger + OpenAPI
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/api-docs/**"
                ).permitAll()

                // 🔴 Auth endpoints
                .requestMatchers("/auth/**").permitAll()

                // 🔐 Everything else secured
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
