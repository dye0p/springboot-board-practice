package com.springboot.board.springboot_board.global.exception;

import com.springboot.board.springboot_board.global.exception.custom.MailException;
import com.springboot.board.springboot_board.global.exception.custom.MemberException;
import com.springboot.board.springboot_board.global.exception.errorcode.ErrorCode;
import com.springboot.board.springboot_board.global.response.ApiResponse;
import com.springboot.board.springboot_board.global.response.FailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> methodArgumentNotValidException(
            final MethodArgumentNotValidException exception) {
        String defaultMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        log.warn(defaultMessage, exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(FailResponse.fail(HttpStatus.BAD_REQUEST, defaultMessage));
    }

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomMemberException(final CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        log.warn(errorCode.message(), exception);

        return ResponseEntity.status(errorCode.httpStatus())
                .body(FailResponse.fail(errorCode.httpStatus(), errorCode.message()));
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomMailException(final CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        log.warn(errorCode.message(), exception);

        return ResponseEntity.status(errorCode.httpStatus())
                .body(FailResponse.fail(errorCode.httpStatus(), errorCode.message()));
    }
}

