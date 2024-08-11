package com.springboot.board.springboot_board.application.mail.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MailSendRequest(
        @NotBlank(message = "이메일을 입력해주세요")
        @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
        String email
) {
}
