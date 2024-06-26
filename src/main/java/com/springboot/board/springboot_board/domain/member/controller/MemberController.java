package com.springboot.board.springboot_board.domain.member.controller;

import com.springboot.board.springboot_board.domain.member.dto.MemberLoginRequest;
import com.springboot.board.springboot_board.domain.member.dto.MemberLoginResponse;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveRequest;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveResponse;
import com.springboot.board.springboot_board.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/v1/join")
    public ResponseEntity<MemberSaveResponse> joinMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest) {
        MemberSaveResponse member = memberService.join(memberSaveRequest);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/v1/login")
    public ResponseEntity<MemberLoginResponse> loginMember(@RequestBody @Valid MemberLoginRequest memberLoginRequest) {
        MemberLoginResponse member = memberService.login(memberLoginRequest);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/v1/check-email")
    public ResponseEntity<String> checkEmailDuplicate(@RequestParam String email) {
        memberService.checkEmailDuplicate(email);
        return ResponseEntity.ok().body("이 이메일은 사용가능 이메일 입니다");
    }

    @GetMapping("/v1/check-loginid")
    public ResponseEntity<String> checkLoginIdDuplicate(@RequestParam String loginId) {
        memberService.checkLonginIdDuplicate(loginId);
        return ResponseEntity.ok().body("이 아이디는 사용가능 아이디 입니다");
    }
}
