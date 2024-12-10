package org.hyunjooon.communication_devtools.global.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    public String createToken(String email, Long expiration, String JWT_SECRET) {
        Map<String, Object> headers = new HashMap<>();
        Claims claims = Jwts.claims();

        headers.put("typ", "JWT");
        headers.put("alg", "HS256");
        claims.put("email", email);

        SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(JWT_SECRET)); // JWT_SECRET_KEY를 Base64 decoding

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
