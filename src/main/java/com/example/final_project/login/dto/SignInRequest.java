package com.example.final_project.login.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SignInRequest {
    private String email;
    private String password;
}
