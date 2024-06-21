package com.springboot.board.springboot_board.domain.member.service;

import com.springboot.board.springboot_board.domain.member.dto.MemberSaveRequest;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveResponse;
import com.springboot.board.springboot_board.domain.member.entity.Member;
import com.springboot.board.springboot_board.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberSaveResponse join(MemberSaveRequest memberSaveRequest) { //요청 DTO를 매개변수로 받음

        Member member = memberRepository.save(memberSaveRequest.toEntity());

        return new MemberSaveResponse(
                member.getId(),
                member.getNickname(),
                member.getEmail(),
                member.getRole()
        );
    }
}