package com.springboot.board.springboot_board.domain.mail.controller;

import com.springboot.board.springboot_board.domain.common.response.ApiResponse;
import com.springboot.board.springboot_board.domain.common.response.SuccessResponse;
import com.springboot.board.springboot_board.domain.mail.service.MailAuthCodeService;
import com.springboot.board.springboot_board.domain.mail.service.MailService;
import com.springboot.board.springboot_board.domain.mail.dto.MailSendRequest;
import com.springboot.board.springboot_board.domain.mail.dto.MailVerifyRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MailAuthController {

    private final MailService mailService;
    private final MailAuthCodeService mailAuthCodeService;

    @PostMapping("/v2/auth/auth-code")
    public ResponseEntity<ApiResponse<Void>> sendEmail(@RequestBody @Valid MailSendRequest mailSendRequest) {
        mailService.checkAndSendEmail(mailSendRequest);
        return ResponseEntity.ok(SuccessResponse.ok("인증코드가 전송되었습니다"));
    }

    @GetMapping("/v2/auth/auth-code")
    public ResponseEntity<ApiResponse<Void>> verifyEmail(@RequestBody @Valid MailVerifyRequest mailVerifyRequest) {
        mailAuthCodeService.verifyAuthCode(mailVerifyRequest);
        return ResponseEntity.ok(SuccessResponse.ok("인증되었습니다"));
    }
}