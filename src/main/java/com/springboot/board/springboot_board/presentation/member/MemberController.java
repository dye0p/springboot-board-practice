package com.springboot.board.springboot_board.presentation.member;

import com.springboot.board.springboot_board.application.member.MemberService;
import com.springboot.board.springboot_board.application.member.dto.request.MemberSaveRequest;
import com.springboot.board.springboot_board.application.member.dto.response.MemberSaveResponse;
import com.springboot.board.springboot_board.global.response.ApiResponse;
import com.springboot.board.springboot_board.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/v1/join")
    public ResponseEntity<ApiResponse<MemberSaveResponse>> joinMember(
            @RequestBody @Valid MemberSaveRequest memberSaveRequest) {
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
        return ResponseEntity.ok(SuccessResponse.ok("사용 가능한 아이디 입니다"));
    }
}
