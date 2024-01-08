package com.example.final_project.login.dto;

import com.example.final_project.login.Exception.MemberErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberErrorResponse {
    private MemberErrorCode errorCode;
    private String errorMessage;
}
