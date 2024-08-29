package com.springboot.board.springboot_board.global.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class JwtPropertiesTest {

    @DisplayName("JwtProperties의 secretKey가 암호화 되어 초기화 된다.")
    @Test
    void JwtProperties() {
        //given
        String secretKey = "testSecretKey12345testSecretKey12345testSecretKey12345";
        Long accessExpirationTime = 1L;
        Long refreshExpirationTime = 1L;

        //when
        JwtProperties jwtProperties = new JwtProperties(
                secretKey, accessExpirationTime, refreshExpirationTime);

        //then
        SecretKey expectedSecretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256");
        assertThat(jwtProperties.getSecretKey()).isEqualTo(expectedSecretKey);
    }
}