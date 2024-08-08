package com.springboot.board.springboot_board.domain.member.service;

import com.springboot.board.springboot_board.domain.member.domain.Member;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveRequest;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveResponse;
import com.springboot.board.springboot_board.domain.member.repository.MemberRepository;
import com.springboot.board.springboot_board.global.exception.custom.MemberException;
import com.springboot.board.springboot_board.global.exception.errorcode.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberSaveResponse join(MemberSaveRequest memberSaveRequest) {
        if (memberRepository.existsByLoginId(memberSaveRequest.loginId()))
            throw new MemberException(MemberErrorCode.MEMBER_DUPLICATION);

        Member member = createMember(memberSaveRequest);
        memberRepository.save(member);

        return MemberSaveResponse.ofMember(member);
    }

    public void checkEmailDuplicate(String email) {
        if (memberRepository.existsByEmail(email))
            throw new MemberException(MemberErrorCode.EMAIL_DUPLICATION);
    }

    public void checkLonginIdDuplicate(String loginId) {
        if (memberRepository.existsByLoginId(loginId))
            throw new MemberException(MemberErrorCode.LOGINID_DUPLICATION);
    }

    private Member createMember(MemberSaveRequest memberSaveRequest) {
        return Member.create(memberSaveRequest.loginId(),
                memberSaveRequest.password(),
                memberSaveRequest.nickname(),
                memberSaveRequest.email(),
                memberSaveRequest.phone(),
                passwordEncoder);
    }
}

