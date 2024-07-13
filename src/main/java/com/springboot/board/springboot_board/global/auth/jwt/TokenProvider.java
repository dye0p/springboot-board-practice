package com.springboot.board.springboot_board.global.auth.jwt;


import com.springboot.board.springboot_board.domain.jwt.dto.Tokens;
import com.springboot.board.springboot_board.domain.jwt.dto.TokenPayload;
import com.springboot.board.springboot_board.global.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class TokenProvider {
    private static final String MEMBER_ID = "memberId";
    private static final String ROLE = "role";

    private final JwtProperties jwtProperties;

    public Tokens creatTokens(TokenPayload tokenPayload) {
        return Tokens.of(
                createToken(tokenPayload.memberId(), tokenPayload.role(), jwtProperties.getAccessExpirationTime()),
                createToken(tokenPayload.memberId(), tokenPayload.role(), jwtProperties.getRefreshExpirationTime())
        );
    }

    private String createToken(String memberId, String role, Long expirationTime) {
        Date now = new Date();
        Date expired = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .claim(MEMBER_ID, memberId)
                .claim(ROLE, role)
                .issuedAt(now)
                .expiration(expired)
                .signWith(jwtProperties.getSecretKey())
                .compact();
    }

    public String getLogidId(String token) {
        return Jwts.parser().verifyWith(jwtProperties.getSecretKey()).build().parseSignedClaims(token).getPayload().get(MEMBER_ID,
                String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(jwtProperties.getSecretKey()).build().parseSignedClaims(token).getPayload().get(ROLE,
                String.class);
    }

    public boolean isExpired(String token) {
        return Jwts.parser().verifyWith(jwtProperties.getSecretKey()).build().parseSignedClaims(token).getPayload().getExpiration()
                .before(new Date());
    }

}