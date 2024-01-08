package com.example.final_project.login.Exception;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException{
    private MemberErrorCode memberErrorCode;
    private String detailMessage;

    public MemberException(MemberErrorCode errorCode){
        super(errorCode.getMessage());
        this.memberErrorCode = errorCode;
        this.detailMessage = getDetailMessage();
    }

    public MemberException(MemberErrorCode errorCode , String detailMessage){
        super(detailMessage);
        this.memberErrorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
