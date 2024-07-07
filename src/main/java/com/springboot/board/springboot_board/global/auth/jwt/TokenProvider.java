package com.springboot.board.springboot_board.global.auth.jwt;


import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenProvider {
    private static final String MEMBER_ID = "memberId";
    private static final String ROLE = "role";

    private SecretKey secretKey;

    public TokenProvider(@Value("${jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String createJwt(String memberId, String role, Long expiredMs) {
        Date now = new Date();
        Date expired = new Date(now.getTime() + expiredMs);

        return Jwts.builder()
                .claim(MEMBER_ID, memberId)
                .claim(ROLE, role)
                .issuedAt(now)
                .expiration(expired)
                .signWith(secretKey)
                .compact();
    }

    public String getLogidId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get(MEMBER_ID,
                String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get(ROLE,
                String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration()
                .before(new Date());
    }

}