package com.springboot.board.springboot_board.domain.member.controller;

import com.springboot.board.springboot_board.domain.member.dto.MemberSaveRequest;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveResponse;
import com.springboot.board.springboot_board.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/v1/join")
    public ResponseEntity<MemberSaveResponse> joinMember(@RequestBody MemberSaveRequest memberSaveRequest) {

        MemberSaveResponse member = memberService.join(memberSaveRequest);

        return ResponseEntity.ok(member);
    }
}
