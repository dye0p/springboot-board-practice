package com.springboot.board.springboot_board.domain.member.controller;

import com.springboot.board.springboot_board.domain.common.response.ApiResponse;
import com.springboot.board.springboot_board.domain.member.dto.MemberLoginRequest;
import com.springboot.board.springboot_board.domain.member.service.MemberService;
import com.springboot.board.springboot_board.domain.jwt.dto.Tokens;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/v2/login")
    public ResponseEntity<ApiResponse<Tokens>> dologin(@RequestBody MemberLoginRequest memberLoginRequest) {
        Tokens token = memberService.login(memberLoginRequest);
        return ResponseEntity.ok(ApiResponse.success(token));
    }
}
