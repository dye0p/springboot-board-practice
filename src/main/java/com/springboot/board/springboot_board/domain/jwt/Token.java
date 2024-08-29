package com.springboot.board.springboot_board.domain.jwt;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@RedisHash(value = "refreshToken")
public class Token {
    private static final Long REFRESH_EXPIRATION_TIME = 86400L;

    @Id
    private String loginId;

    private String refreshToken;

    @TimeToLive
    private Long refreshExpirationTime;

    @Builder
    private Token(String loginId, String refreshToken, Long refreshExpirationTime) {
        this.loginId = loginId;
        this.refreshToken = refreshToken;
        this.refreshExpirationTime = refreshExpirationTime;
    }

    public static Token of(String loginId, String refreshToken) {
        return Token.builder()
                .loginId(loginId)
                .refreshToken(refreshToken)
                .refreshExpirationTime(REFRESH_EXPIRATION_TIME)
                .build();
    }
}