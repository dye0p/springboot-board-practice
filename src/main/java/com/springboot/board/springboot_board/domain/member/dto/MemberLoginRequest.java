package com.springboot.board.springboot_board.domain.member.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberLoginRequest(
        @NotBlank(message = "아이디를 입력해주세요")
        String loginId,
        @NotBlank(message = "비밀번호를 입력해주세요")
        String password
) {

}
