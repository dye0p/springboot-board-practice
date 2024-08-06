package com.springboot.board.springboot_board.domain.member.dto;

import com.springboot.board.springboot_board.domain.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberSaveResponse(
        Long id,
        String nickname,
        String email,
        String role
) {

    public static MemberSaveResponse ofMember(Member member) {
        return MemberSaveResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .role(member.getRole().getValue())
                .build();
    }
}
