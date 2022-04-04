package com.shelby.restaurant.shelbysrestaurant.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class JwtUtil {

    @Value("${okta.oauth2.audience}")
    private String oktaAudience;

    @Value("${okta.oauth2.client-id}")
    private String oktaClientId;

    @Value("${okta.oauth2.client-secret}")
    private String oktaClientSecret;

    public String getSignedJwt() {
        final SecretKey secretKey = Keys.hmacShaKeyFor(oktaClientSecret.getBytes(StandardCharsets.UTF_8));
        final Instant now = Instant.now();
        return Jwts.builder()
                .setAudience(oktaAudience)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5L, ChronoUnit.MINUTES)))
                .setIssuer(oktaClientId)
                .setSubject(oktaClientId)
                .setId(UUID.randomUUID().toString())
                .signWith(secretKey)
                .compact();
    }
}
