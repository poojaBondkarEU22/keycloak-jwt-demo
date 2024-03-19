package com.security.keycloak.jwt;

import org.keycloak.KeycloakSecurityContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class KeycloakJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakJwtApplication.class, args);
	}

	@Bean
	public KeycloakSecurityContext getKeyCloakSecurityContext() {
		return new KeycloakSecurityContext();
	}

}
