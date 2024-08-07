package com.springboot.board.springboot_board.domain.mail.util;

import com.springboot.board.springboot_board.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@RequiredArgsConstructor
@Component
public class AuthCodeGenerator {

    private final RedisUtil redisUtil;

    private static final String AUTH_CODE_PREFIX = "AUTH_CODE_";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 8;
    private static final long AUTH_CODE_EXPIRE_DURATION = 5 * 60 * 1000; // 5분 (밀리초)

    public void saveAuthCode(String email, String authCode) {
        redisUtil.saveData(AUTH_CODE_PREFIX + email,authCode, AUTH_CODE_EXPIRE_DURATION);
    }

    public String generateAuthCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }

    public boolean verifyAuthCode(String email, String authCode) {
        String storedAuthCode = redisUtil.getData(AUTH_CODE_PREFIX + email);
        if (storedAuthCode != null && storedAuthCode.equals(authCode)) {
            redisUtil.deleteData(AUTH_CODE_PREFIX + email); // 인증이 성공하면 Redis에서 삭제
            return true;
        }
        return false;
    }
}

