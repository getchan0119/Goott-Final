package com.example.final_project.login.Exception;

import com.example.final_project.login.dto.MemberErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.final_project.login.Exception.MemberErrorCode.INVALID_REQUEST;


@Slf4j
@RestControllerAdvice
public class MemberExceptionHandler {
    @ExceptionHandler(MemberException.class)
    public MemberErrorResponse handleException(MemberException e, HttpServletRequest request) {
        log.error("errorCode: {}, url: {}, message: {}", e.getMemberErrorCode(), request.getRequestURI() , e.getDetailMessage());

        return MemberErrorResponse.builder()
                .errorCode(e.getMemberErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class
    })
    public MemberErrorResponse handleBadRequest(
            Exception e, HttpServletRequest request
    ) {
        log.error("errorCode: {}, url: {}, message: {}", request.getRequestURI());

        return MemberErrorResponse.builder()
                .errorCode(INVALID_REQUEST)
                .errorMessage(INVALID_REQUEST.getMessage())
                .build();
    }
}
