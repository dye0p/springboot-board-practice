package com.springboot.board.springboot_board.domain.jwt.domain;

import com.springboot.board.springboot_board.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id") //member의 PK를 참조
    private Member member;

    @Column(nullable = false)
    private String refreshToken;

    @Builder
    private Token(Member member, String refreshToken) {
        this.member = member;
        this.refreshToken = refreshToken;
    }

    public static Token of(Long memberId, String refreshToken) {
        return Token.builder()
                .member(Member.fromId(memberId))
                .refreshToken(refreshToken)
                .build();
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}