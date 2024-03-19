package com.security.keycloak.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/v2/keycloak")
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Home");
    }


    @PostMapping("/registerUser")
    public ResponseEntity<String> registerNewUser() {
        return ResponseEntity.ok("New User registered. Please login.");
    }

    @GetMapping
    @PreAuthorize("hasRole('client_user2')")
    public ResponseEntity<String> getMsg(Principal principal) {

        return ResponseEntity.ok("You secured with jwt token from keycloak :: "+ principal.getName());
    }

    @GetMapping("/hello2")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<String> getMsgForAdmin() {
        return ResponseEntity.ok("Admin :: You secured with jwt token from keycloak");
    }

    @PostMapping
    public ResponseEntity<String> testPostMapping() {
        return ResponseEntity.ok("test post mapping");
    }
}
