package com.example.final_project.login.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {
    Duplicate_Email("이미 등록된 이메일 주소입니다."),
    Password_Incorrect("2개의 패스워드가 일치하지않습니다."),

    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다.");

    private final String message;
}
