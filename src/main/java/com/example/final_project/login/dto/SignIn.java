package com.example.final_project.login.dto;

import lombok.*;

public class SignIn {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request {
        private String email;
        private String password;
    }
}
