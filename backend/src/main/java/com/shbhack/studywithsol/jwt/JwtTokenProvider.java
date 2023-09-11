package com.shbhack.studywithsol.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.security.Keys;


@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String salt;

    private Key secretKey;

    private final Long expireTimeMS = 1000L * 60 * 60 *24;

    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Long userId, String id){
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("id" ,id);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMS))
                .signWith(secretKey)
                .compact();
    }



}
