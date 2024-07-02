package com.springboot.board.springboot_board.domain.member.service;

import com.springboot.board.springboot_board.domain.member.dto.mail.MailVerifyRequest;
import com.springboot.board.springboot_board.domain.member.util.AuthCodeGenerator;
import com.springboot.board.springboot_board.global.exception.CustomException;
import com.springboot.board.springboot_board.global.exception.errorcode.MailErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MailAuthCodeService {

    private final AuthCodeGenerator authCodeGenerator;

    @Transactional(readOnly = true)
    public void verifyAuthCode(MailVerifyRequest mailVerifyRequest) {
        if (!authCodeGenerator.verifyAuthCode(mailVerifyRequest.email(), mailVerifyRequest.verifyCode())) {
            throw new CustomException(MailErrorCode.INVALID_VERIFICATION_CODE);
        }
    }
}
