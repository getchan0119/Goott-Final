package com.example.final_project.login.service;

import com.example.final_project.login.Exception.MemberException;
import com.example.final_project.login.dto.MemberCreateDto;
import com.example.final_project.login.entity.Member;
import com.example.final_project.login.repository.MemberRepository;
import com.example.final_project.login.type.UserRole;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.final_project.login.Exception.MemberErrorCode.*;


@Service    //비즈니스 로직 담당
@RequiredArgsConstructor // 아래에 주석을 자동으로 만들어줌 (생성자)
public class MemberService implements UserDetailsService{
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
//                .memberType("user")
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


    // 로그인 시 사용할 스프링 시큐리티
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                member.getEmail(),
                member.getPassword(),
                getAuthorities(member.getMemberType())
        );
    }

    private static List<GrantedAuthority> getAuthorities(String memberType) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(memberType)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        return authorities;
    }
}
