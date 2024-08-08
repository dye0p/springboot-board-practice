package com.springboot.board.springboot_board.domain.jwt.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "blackListToken")
public class BlackList {

    @Id
    private String accessToken;

    @TimeToLive
    private Long expirationTime;

    @Builder
    protected BlackList(String accessToken, Long expirationTime) {
        this.accessToken = accessToken;
        this.expirationTime = expirationTime;
    }
    public static BlackList of(String accessToken, Long expirationTime) {
        return BlackList.builder()
                .accessToken(accessToken)
                .expirationTime(expirationTime)
                .build();
    }
}
