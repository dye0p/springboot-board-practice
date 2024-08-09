package com.springboot.board.springboot_board.domain.member.service;

import com.springboot.board.springboot_board.domain.member.business.MemberCreator;
import com.springboot.board.springboot_board.domain.member.business.MemberValidator;
import com.springboot.board.springboot_board.domain.member.domain.Member;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveResponse;
import com.springboot.board.springboot_board.domain.member.dto.request.MemberSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberCreator memberCreator;
    private final MemberValidator memberValidator;

    @Transactional
    public MemberSaveResponse join(final MemberSaveRequest memberSaveRequest) {
        memberValidator.validateDuplicatedToEmail(memberSaveRequest.email());

        Member member = memberCreator.creatMember(memberSaveRequest);

        return MemberSaveResponse.of(member);
    }

    public void checkEmailDuplicate(final String email) {
        memberValidator.validateDuplicatedToEmail(email);
    }

    public void checkLonginIdDuplicate(final String loginId) {
        memberValidator.validateDuplicatedToLoginId(loginId);
    }
}

