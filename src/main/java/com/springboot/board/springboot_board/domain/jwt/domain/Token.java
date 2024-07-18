package com.springboot.board.springboot_board.domain.jwt.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@RedisHash(value = "refreshToken", timeToLive = 60) //test를 위해서 만료시간을 1분으로 설정
public class Token {

    @Id
    private String loginId;

    @Indexed
    private String refreshToken;

    @Builder
    private Token(String loginId, String refreshToken) {
        this.loginId = loginId;
        this.refreshToken = refreshToken;
    }

    public static Token of(String loginId, String refreshToken) {
        return new Token(loginId, refreshToken);
    }
}