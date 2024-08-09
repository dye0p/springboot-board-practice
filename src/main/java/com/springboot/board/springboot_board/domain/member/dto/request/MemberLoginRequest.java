package com.springboot.board.springboot_board.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberLoginRequest(
        @NotBlank(message = "아이디 또는 비밀번호를 입력해주세요")
        String loginId,

        @NotBlank(message = "아이디 또는 비밀번호를 입력해주세요")
        String password
) {

}
