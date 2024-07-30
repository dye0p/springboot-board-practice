package com.springboot.board.springboot_board.global.exception.custom;

import com.springboot.board.springboot_board.global.exception.CustomException;
import com.springboot.board.springboot_board.global.exception.errorcode.ErrorCode;

public class MailException extends CustomException {

    public MailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
