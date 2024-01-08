package com.example.final_project.login.dto;

import com.example.final_project.login.entity.Member;
import jakarta.validation.constraints.Size;
import lombok.*;

public class MemberCreateDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request {

        private Long id;
        private String name;
        private String password1;
        private String password2;
        @Size(min= 8, max = 50 , message = "이메일은 필수항목입니다.")
        private String email;
        private String memberType;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private Long id;
        private String name;
        private String password;
        private String email;
        private String memberType;

        public static Response fromEntity(Member member){
            return Response.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .password(member.getPassword())
                    .email(member.getEmail())
                    .memberType(member.getMemberType())
                    .build();
        }


    }
}
