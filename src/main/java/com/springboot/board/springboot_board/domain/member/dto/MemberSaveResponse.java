package com.springboot.board.springboot_board.domain.member.dto;

public record MemberSaveResponse(
        Long id,
        String nickname,
        String email,
        String role
) {
}
