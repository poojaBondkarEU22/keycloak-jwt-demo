package com.security.keycloak.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/keycloak")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> getMsg() {
        return ResponseEntity.ok("You secured with jwt token from keycloak");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getMsgForAdmin() {
        return ResponseEntity.ok("Admin :: You secured with jwt token from keycloak");
    }

    @PostMapping
    public ResponseEntity<String> testPostMapping() {
        return ResponseEntity.ok("test post mapping");
    }
}
