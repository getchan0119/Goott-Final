package com.example.final_project.login.dto;


import com.example.final_project.login.entity.Member;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    private Long id;
    private String name;
    private String password;
    private String email;
    private String memberType;

    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .password(member.getPassword())
                .email(member.getEmail())
                .memberType(member.getMemberType())
                .build();
    }


}

