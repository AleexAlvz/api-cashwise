package com.aleexalvz.api.cashwise.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        requests -> requests
                                .requestMatchers("/entry").permitAll()
                                .requestMatchers("/login", "/logout").permitAll()
                                .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public String passwordEncoder() {
        return "passwordEncoder";
    }
}