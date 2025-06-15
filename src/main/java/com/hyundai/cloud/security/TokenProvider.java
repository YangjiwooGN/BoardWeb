package com.hyundai.cloud.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;


// JWT: 전자 서명이 된 토큰
// JSON: 형태로 구성된 토큰
// {header}.{payload}.{signature}

// header: typ (해당 토큰의 타입), alg (토큰을 사용하기 위해 사용된 해시 알고리즘)
// payload: sub (해당 토큰의 주인), iat (토큰이 발행된 시간), exp (토큰이 만료되는 시간)

@Service
public class TokenProvider {
    // JWT 생성 및 검증을 위한 키 생성
    private static final String SECURITY_KEY = "jwtseckeyjwtseckeyjwtseckeyjwtseckeyjwtseckeyjwtseckeyjwtseckeyjwtseckey"; // 64+자
    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECURITY_KEY.getBytes(StandardCharsets.UTF_8));

    // JWT 생성하는 메서드
    public String create(String userEmail){
        // 만료 날짜를 현재 날짜 + 1시간으로 설정
        Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        // JWT를 생성
        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(new Date())
                .setExpiration(exprTime)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    // JWT 검증
    public String validate(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
