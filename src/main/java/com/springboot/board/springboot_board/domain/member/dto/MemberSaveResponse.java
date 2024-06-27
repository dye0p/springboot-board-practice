package com.springboot.board.springboot_board.domain.member.dto;

import com.springboot.board.springboot_board.domain.member.domain.Member;

public record MemberSaveResponse(
        Long id,
        String nickname,
        String email,
        String role
) {
    public static MemberSaveResponse ofMember(Member member) {
        return new MemberSaveResponse(
                member.getId(),
                member.getNickname(),
                member.getEmail(),
                member.getRole().getValue()
        );
    }
}
