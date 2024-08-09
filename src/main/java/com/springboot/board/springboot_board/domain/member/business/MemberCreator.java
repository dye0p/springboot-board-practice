package com.springboot.board.springboot_board.domain.member.business;

import com.springboot.board.springboot_board.domain.member.domain.Member;
import com.springboot.board.springboot_board.domain.member.dto.request.MemberSaveRequest;
import com.springboot.board.springboot_board.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberCreator {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member creatMember(MemberSaveRequest memberSaveRequest) {

        Member member = Member.create(memberSaveRequest.loginId(),
                memberSaveRequest.password(),
                memberSaveRequest.nickname(),
                memberSaveRequest.email(),
                memberSaveRequest.phone(),
                passwordEncoder);

        memberRepository.save(member);

        return member;
    }
}

