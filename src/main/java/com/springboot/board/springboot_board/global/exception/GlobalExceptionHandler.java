package com.springboot.board.springboot_board.global.exception;

import com.springboot.board.springboot_board.domain.common.response.ApiResponse;
import com.springboot.board.springboot_board.global.exception.custom.MailException;
import com.springboot.board.springboot_board.global.exception.custom.MemberException;
import com.springboot.board.springboot_board.global.exception.errorcode.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "Validation error occurred";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(errorMessage));
    }

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomMemberException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(errorCode.httpStatus())
                .body(ApiResponse.error(errorCode.message()));
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomMailException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(errorCode.httpStatus())
                .body(ApiResponse.error(errorCode.message()));
    }
}
