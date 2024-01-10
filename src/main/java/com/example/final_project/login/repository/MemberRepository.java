package com.example.final_project.login.repository;

import com.example.final_project.login.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일 중복체크를 위함
    Optional<Member> findByEmail(String email);

}
