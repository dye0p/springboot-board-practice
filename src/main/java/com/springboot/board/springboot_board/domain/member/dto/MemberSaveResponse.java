package com.springboot.board.springboot_board.domain.member.dto;

import com.springboot.board.springboot_board.domain.Role;

public record MemberSaveResponse(
        Long id,
        String nickname,
        String email,
        Role role
) {
}
