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

    private static Key secretKey;

    private static final Long expireTimeMS = 1000L * 60 * 60 *24;


    public static Long getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("userId", Long.class);
    }

    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public static String createToken(Long userId){
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMS))
                .signWith(secretKey)
                .compact();
    }

    public static boolean isExpired(String token){
//        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
//        return !claims.getBody().getExpiration().before(new Date());
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

}
