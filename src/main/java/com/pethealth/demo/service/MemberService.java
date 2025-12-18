package com.pethealth.demo.service;

import com.pethealth.demo.domain.Member;
import com.pethealth.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

@Service // 1. 이 클래스가 Spring의 "서비스"임을 선언
public class MemberService {

    private final MemberRepository memberRepository;

    // 2. Repository를 주입(연결)
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional // 3. 이 메서드가 DB 작업을 함을 선언
    public Member registerUser(Member member) {
        // 4. 이메일 중복 확인
        if (isEmailExists(member.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + member.getEmail());
        }

        // 5. 가입 시간 설정
        member.setCreatedAt(LocalDateTime.now());
        
        // 6. DB에 저장
        return memberRepository.save(member);
    }

    private boolean isEmailExists(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}