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

    /**
     * Main.java의 registerUser 로직을 가져온 메서드
     */
    @Transactional // 3. 이 메서드가 DB 작업을 함을 선언 (중요)
    public Member registerUser(Member member) {
        // 4. 이메일 중복 확인 (Main.java의 로직)
        if (isEmailExists(member.getEmail())) {
            // Main.java에서처럼 예외(Exception)를 발생시킴
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + member.getEmail());
        }

        // 5. 가입 시간 설정 (Controller에서 여기로 이동)
        member.setCreatedAt(LocalDateTime.now());
        
        // 6. DB에 저장
        return memberRepository.save(member);
    }

    /**
     * Main.java의 isEmailExists 로직을 가져온 메서드
     */
    private boolean isEmailExists(String email) {
        // Repository를 이용해 DB에서 이메일을 검색
        // .isPresent() : 검색 결과가 존재하는지 (true/false) 반환
        return memberRepository.findByEmail(email).isPresent();
    }

    // (참고) 회원 전체 조회 기능도 Service로 옮길 수 있습니다.
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}