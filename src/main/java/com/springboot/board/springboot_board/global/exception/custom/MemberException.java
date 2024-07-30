package com.springboot.board.springboot_board.global.exception.custom;

import com.springboot.board.springboot_board.global.exception.CustomException;
import com.springboot.board.springboot_board.global.exception.errorcode.ErrorCode;

public class MemberException extends CustomException {

    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
