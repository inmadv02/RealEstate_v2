package com.salesianostriana.dam.RealEstate_v2.security.jwt;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Log
@Service
public class JwtProvider {

    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret:luegolocambio}")
    private String jwtSecret;

    @Value("${jwt.duration:86400")
    private int jwtLifeInSeconds;

    private JwtParser parser;

    @PostConstruct
    public void init () {
        parser = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build();
    }

    public String generateToken(Authentication authentication) {
        return null;
    }


}
