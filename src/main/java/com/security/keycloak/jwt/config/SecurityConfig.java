package com.security.keycloak.jwt.config;

import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity

public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    private final LogoutService logoutService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         return http.csrf(AbstractHttpConfigurer::disable)
             .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("api/v2/keycloak/registerUser").permitAll()
                                .anyRequest().authenticated())
                 .oauth2ResourceServer(oauth -> oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
                 .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// need to remove when using with redis to store session
                /* .logout(logout -> {
                     logout.logoutUrl("/api/v2/keycloak/logout").permitAll();
                     logout.addLogoutHandler(logoutService);
                     logout.logoutSuccessUrl("/api/v2/keycloak/home").permitAll();
                 })*/
                 .build();


    }
}
