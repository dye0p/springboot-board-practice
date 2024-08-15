package com.springboot.board.springboot_board.application.jwt;


import com.springboot.board.springboot_board.application.jwt.dto.TokenPayload;
import com.springboot.board.springboot_board.application.jwt.dto.Tokens;
import com.springboot.board.springboot_board.global.exception.custom.JwtAuthenticationException;
import com.springboot.board.springboot_board.global.exception.errorcode.TokenErrorCode;
import com.springboot.board.springboot_board.global.properties.JwtProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenProvider {
    private static final String MEMBER_ID = "memberId";
    private static final String ROLE = "role";

    private final JwtProperties jwtProperties;

    public Tokens creatTokens(TokenPayload tokenPayload) {
        return Tokens.of(
                createAccessToken(tokenPayload.memberId(), tokenPayload.role(), jwtProperties.getAccessExpirationTime()),
                createRefreshToken(jwtProperties.getRefreshExpirationTime())
        );
    }

    private String createAccessToken(String memberId, String role, Long expirationTime) {
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

    private String createRefreshToken(Long expirationTime) {
        Date now = new Date();
        Date expired = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .issuedAt(now)
                .expiration(expired)
                .signWith(jwtProperties.getSecretKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(jwtProperties.getSecretKey())
                    .build().parseSignedClaims(token);
            return true;
        } catch (SecurityException | SignatureException | MalformedJwtException exception) {
            log.warn("유효하지 않은 JWT 토큰", exception);
            throw new JwtAuthenticationException(TokenErrorCode.AUTH_INVALID_TOKEN);
        } catch (ExpiredJwtException exception) {
            log.warn("만료된 JWT 토큰", exception);
            throw new JwtAuthenticationException(TokenErrorCode.AUTH_TOKEN_HAS_EXPIRED);
        } catch (UnsupportedJwtException exception) {
            log.warn("지원되지 않는 JWT 토큰", exception);
            throw new JwtAuthenticationException(TokenErrorCode.AUTH_TOKEN_IS_UNSUPPORTED);
        } catch (IllegalArgumentException exception) {
            log.warn("JWT 클레임 문자열이 비어 있습니다.", exception);
            throw new JwtAuthenticationException(TokenErrorCode.AUTH_IS_NULL);
        }
    }

    public String getLogidId(String token) {
        return Jwts.parser().verifyWith(jwtProperties.getSecretKey())
                .build().parseSignedClaims(token).getPayload().get(MEMBER_ID, String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(jwtProperties.getSecretKey())
                .build().parseSignedClaims(token).getPayload().get(ROLE, String.class);
    }

    public long getRemainingExpirationTime(String token) {
        Date expiryDate = getExpiryDate(token);
        long now = new Date().getTime();
        return expiryDate.getTime() - now;
    }

    private Date getExpiryDate(String token) {
        return Jwts.parser().verifyWith(jwtProperties.getSecretKey())
                .build().parseSignedClaims(token).getPayload().getExpiration();
    }
}