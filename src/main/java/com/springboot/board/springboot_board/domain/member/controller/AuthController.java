package com.springboot.board.springboot_board.domain.member.controller;

import com.springboot.board.springboot_board.domain.common.response.ApiResponse;
import com.springboot.board.springboot_board.domain.jwt.dto.Tokens;
import com.springboot.board.springboot_board.domain.member.dto.MemberLoginRequest;
import com.springboot.board.springboot_board.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/v2/login")
    public ResponseEntity<ApiResponse<Tokens>> doLogin(@RequestBody MemberLoginRequest memberLoginRequest) {
        Tokens token = memberService.login(memberLoginRequest);
        return ResponseEntity.ok(ApiResponse.success(token));
    }


    @GetMapping("/v1/logout")
    public ResponseEntity<ApiResponse<Void>> doLogout(@RequestHeader("accessToken") String accessToken) {
        String token = accessToken.split(" ")[1].trim();
//        System.out.println(token);
        memberService.logout(token);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @GetMapping("/v1/hello")
    public String access() {
        return "seucces Test";
    }
}
