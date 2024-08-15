package com.springboot.board.springboot_board.application.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MailVerifyRequest(
        @NotBlank(message = "인증번호를 입력해주세요")
        @Size(min = 8, max = 8, message = "인증코드는 8자리 입니다.")
        String verifyCode,

        String email
) {
}
