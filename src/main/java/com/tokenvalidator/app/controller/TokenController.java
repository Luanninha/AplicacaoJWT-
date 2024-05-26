package com.tokenvalidator.app.controller;

import com.tokenvalidator.app.model.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

@RestController
public class TokenController {

   

    @PostMapping(value = "/validate")
    public boolean validate(@RequestBody Token token) {
        if (token == null || token.getToken() == null || token.getToken().isEmpty()) {
            return false;
        }
        return validateToken(token.getToken());
    }

    private boolean validateToken(String jwt) {
        try {
            SecretKey key = Jwts.SIG.HS256.key().build();
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(jwt);
            Claims claims = claimsJws.getBody();

            if (claims.size() != 3) {
                return false;
            }

            String name = claims.get("Name", String.class);
            String role = claims.get("Role", String.class);
            Integer seed = claims.get("Seed", Integer.class);

            if (name == null || name.length() > 256 || name.matches(".*\\d.*")) {
                return false;
            }
            if (!("Admin".equals(role) || "Member".equals(role) || "External".equals(role))) {
                return false;
            }
            if (!isPrime(seed)) {
                return false;
            }

            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}