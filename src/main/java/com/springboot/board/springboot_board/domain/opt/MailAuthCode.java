package com.springboot.board.springboot_board.domain.opt;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.security.SecureRandom;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "mailAuthCode")
public class MailAuthCode {

    private static final Long EMAIL_AUTH_CODE_EXPIRATION_TIME = 300L;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 8;

    @Id
    private String email;
    private String authCode;
    private boolean authState;

    @TimeToLive
    private Long expirationTime;

    @Builder
    protected MailAuthCode(String email, String authCode, boolean authState, Long expirationTime) {
        this.email = email;
        this.authCode = authCode;
        this.authState = authState;
        this.expirationTime = expirationTime;
    }

    public static MailAuthCode of(String email) {
        return MailAuthCode.builder()
                .email(email)
                .authCode(generateAuthCode())
                .authState(false)
                .expirationTime(EMAIL_AUTH_CODE_EXPIRATION_TIME)
                .build();
    }

    private static String generateAuthCode() {
        SecureRandom random = new SecureRandom();
        return generateRandomCode(random);
    }

    private static String generateRandomCode(SecureRandom random) {
        StringBuilder authCode = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            authCode.append(randomCharacter(random));
        }
        return authCode.toString();
    }

    private static char randomCharacter(SecureRandom random) {
        return CHARACTERS.charAt(getIndex(random));
    }

    private static int getIndex(SecureRandom random) {
        return random.nextInt(CHARACTERS.length());
    }
}
