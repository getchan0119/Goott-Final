package com.example.final_project.login.service;

import com.example.final_project.login.Exception.MemberException;
import com.example.final_project.login.dto.MemberCreateDto;
import com.example.final_project.login.entity.Member;
import com.example.final_project.login.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.final_project.login.Exception.MemberErrorCode.Duplicate_Email;
import static com.example.final_project.login.Exception.MemberErrorCode.Password_Incorrect;


@Service    //비즈니스 로직 담당
@RequiredArgsConstructor // 아래에 주석을 자동으로 만들어줌 (생성자)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager em;


    // 회원가입
    @Transactional
    public MemberCreateDto.Response createMember(MemberCreateDto.Request request){
        validateCreateMemberRequest(request); // 유효성 검사

        //비즈니스 로직
        String encodedPassword = passwordEncoder.encode(request.getPassword1());

        Member member = Member.builder()
                .id(request.getId())
                .name(request.getName())
                .password(encodedPassword)
                .email(request.getEmail())
                .memberType("user")
                .build();

        // 비밀번호 일치 여부 확인
        if (!request.getPassword1().equals(request.getPassword2())) {
            throw new MemberException(Password_Incorrect); // 비밀번호 불일치
        }
        // 레포지토리를 통해 데이터베이스에 저장
        Member savedMember = memberRepository.save(member);

        return MemberCreateDto.Response.fromEntity(savedMember);
    }

    // 유효성 검사
    private void validateCreateMemberRequest(MemberCreateDto.Request request) {
        // 이메일 중복 검사
        memberRepository.findByEmail(request.getEmail())
                .ifPresent((member -> {
                    throw new MemberException(Duplicate_Email); //이메일 중복
                }));


    }

    // 화면에서 이메일 중복체크 메서드
    public boolean isEmailDuplicate(String email) {
        Optional<Member> existingMember = memberRepository.findByEmail(email);
        return existingMember.isPresent();
    }
}
