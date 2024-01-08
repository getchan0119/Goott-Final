package com.example.final_project.login.controller;

import com.example.final_project.login.dto.MemberCreateDto;
import com.example.final_project.login.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor    //서비스를 자동으로 컨트롤러에 인잭션
public class MemberController {

    private final MemberService memberService;

    // 회원가입 폼 페이지 불러오기
    @RequestMapping ("/SignUp")
    public String signup() {
        return "SignUp";
    }

    // 회원가입 데이터 넣기
    @PostMapping("/SignUp")
    public String signup(MemberCreateDto.Request request) {
        memberService.createMember(request);
        return "redirect:/Login";
    }
}
