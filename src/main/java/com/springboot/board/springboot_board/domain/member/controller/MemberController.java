package com.springboot.board.springboot_board.domain.member.controller;

import com.springboot.board.springboot_board.domain.common.response.ApiResponse;
import com.springboot.board.springboot_board.domain.common.response.SuccessResponse;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveRequest;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveResponse;
import com.springboot.board.springboot_board.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/v1/join")
    public ResponseEntity<ApiResponse<MemberSaveResponse>> joinMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest) {
        MemberSaveResponse member = memberService.join(memberSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.of(HttpStatus.CREATED, member));
    }

    @GetMapping("/v1/check-email")
    public ResponseEntity<ApiResponse<String>> checkEmailDuplicate(@RequestParam String email) {
        memberService.checkEmailDuplicate(email);
        return ResponseEntity.ok(SuccessResponse.ok("사용 가능한 이메일 입니다"));
    }

    @GetMapping("/v1/check-loginid")
    public ResponseEntity<ApiResponse<String>> checkLoginIdDuplicate(@RequestParam String loginId) {
        memberService.checkLonginIdDuplicate(loginId);
        return ResponseEntity.ok(SuccessResponse.of("사용 가능한 아이디 입니다"));
    }
}
