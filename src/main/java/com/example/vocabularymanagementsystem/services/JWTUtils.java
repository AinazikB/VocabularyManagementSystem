package com.example.vocabularymanagementsystem.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JWTUtils {
    private static final long EXPIRATION_TIME = 86400000;
    private static final String SECRET_KEY = "3948058306098640539430438539736734964084308604986343252352365346426424757576576852859486";
    private static final String HMAC_SHA256 = "HmacSHA256";

    private SecretKey key;

    private JWTUtils() {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
            this.key = new SecretKeySpec(keyBytes, HMAC_SHA256);
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка декодирования секретного ключа: " + e.getMessage());
        }
    }

    public String generatedToken(UserDetails userDetails){
        String email = userDetails.getUsername();
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String generatedRefreshToken(HashMap<String, Object> claims, UserDetails userDetails){
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

}