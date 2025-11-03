package com.pethealth.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pethealth.demo.domain.Member;

// JpaRepository<관리할 Entity, 그 Entity의 ID 타입>
public interface MemberRepository extends JpaRepository<Member, Long> {
    
    // 이메일로 회원을 찾는 기능 (Spring Data JPA가 이름만 보고 자동 구현)
    Member findByEmail(String email);
}