package com.ingressou.Ingressou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desativa a proteção CSRF (caso esteja usando)
                .authorizeHttpRequests(auth -> auth // Usando lambda para configuração de autorização
                        .requestMatchers("/api/usuarios").permitAll() // Permite acesso sem autenticação
                        .anyRequest().authenticated()); // Exige autenticação para qualquer outra requisição

        return http.build();
    }
}
