package com.springboot.board.springboot_board.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class RedisUtil {

    private final StringRedisTemplate redisTemplate;

    private static final long EMAIL_VERIFICATION_LIMIT_IN_SECONDS = 5 * 60 * 1000; // 5분 (밀리초)

    public String getAuthCode(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    public void saveAuthCode(String email, String authCode) {
        redisTemplate.opsForValue()
                .set(email, authCode,
                        Duration.ofSeconds(EMAIL_VERIFICATION_LIMIT_IN_SECONDS));
    }

    public void deleteData(String email) {
        redisTemplate.delete(email);
    }
}
