package com.springboot.board.springboot_board.application.auth;

import com.springboot.board.springboot_board.domain.opt.AuthCodeRepository;
import com.springboot.board.springboot_board.domain.opt.MailAuthCode;
import com.springboot.board.springboot_board.global.exception.custom.MailException;
import com.springboot.board.springboot_board.global.exception.errorcode.MailErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthCodeVerifier {

    private final AuthCodeRepository authCodeRepository;

    public void verifyAuthCode(String email, String inputAuthCode) {
        MailAuthCode mailAuthCode = findMailAuthCodeBy(email);
        validateAuthCode(mailAuthCode, inputAuthCode);
        removeAuthCode(email);
    }

    private void validateAuthCode(MailAuthCode mailAuthCode, String inputAuthCode) {
        if (!isValidAuthCode(mailAuthCode, inputAuthCode)) {
            throw new MailException(MailErrorCode.INVALID_VERIFICATION_CODE);
        }
    }

    private MailAuthCode findMailAuthCodeBy(String email) {
        return authCodeRepository.findById(email).orElse(null);
    }

    private boolean isValidAuthCode(MailAuthCode mailAuthCode, String inputAuthCode) {
        return mailAuthCode != null && mailAuthCode.getAuthCode().equals(inputAuthCode);
    }

    private void removeAuthCode(String email) {
        authCodeRepository.deleteById(email);
    }
}
