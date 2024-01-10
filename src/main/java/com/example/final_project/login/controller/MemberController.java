package com.example.final_project.login.controller;

import com.example.final_project.login.Exception.MemberException;
import com.example.final_project.login.dto.MemberCreateDto;
import com.example.final_project.login.dto.MemberErrorResponse;
import com.example.final_project.login.dto.SignIn;
import com.example.final_project.login.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor    //서비스를 자동으로 컨트롤러에 인잭션
public class MemberController {

    private final MemberService memberService;

    // 회원가입 폼 페이지 불러오기
    @GetMapping ("/signup")
    public String signupForm() {
        return "signup";
    }

    // 회원가입 데이터 넣기
    @PostMapping("/signup")
    public String signup(@RequestBody MemberCreateDto.Request request) {
        memberService.createMember(request);
        return "redirect:/signin";
    }

    //  로그인 폼페이지
    @GetMapping ("/signin")
    public String signinForm() {
        return "signin";
    }

    // 로그인 데이터 넣기
    @PostMapping("/signin")
    public String signin(@RequestBody SignIn.Request request)  {
        memberService.loadUserByUsername(request.getEmail());
        return "redirect:/";
    }

    // 이메일 중복 검사
    @GetMapping("/check-duplicate")
    public ResponseEntity<Map<String, Boolean>> checkDuplicateEmail(@RequestParam String email) {
        boolean isDuplicate = memberService.isEmailDuplicate(email);
        Map<String, Boolean> response = Collections.singletonMap("isDuplicate", isDuplicate);
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(MemberException.class)
    public MemberErrorResponse handleException(MemberException e, HttpServletRequest request) {
        log.error("errorCode: {}, url: {}, message: {}", e.getMemberErrorCode(), request.getRequestURI() , e.getDetailMessage());

        return MemberErrorResponse.builder()
                .errorCode(e.getMemberErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }}
