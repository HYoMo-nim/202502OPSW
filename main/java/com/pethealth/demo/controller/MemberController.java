package com.pethealth.demo.controller;

import com.pethealth.demo.domain.Member;
import com.pethealth.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST API의 "창구" 역할을 하는 컨트롤러
 * @RestController : 이 클래스가 API 컨트롤러임을 선언
 * @RequestMapping("/api/members") : 이 컨트롤러의 모든 API는 /api/members 라는
 * 공통 주소로 시작함
 */
@RestController
@RequestMapping("/api/members")
public class MemberController {

    // MemberRepository(데이터 조작기)를 이 컨트롤러에 주입(연결)
    // (이게 바로 의존성 주입, DI 입니다)
    private final MemberRepository memberRepository;

    // 생성자를 통해 MemberRepository를 주입받는 것을 권장
    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * [API 1] 회원 가입 (데이터 생성 - POST)
     * 주소: POST /api/members
     * @param member 클라이언트(앱/웹)가 보낸 회원 정보 (JSON 형태)
     * @return 저장된 회원 정보
     */
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        // 클라이언트가 보낸 정보 외에, 서버에서 생성 시간을 설정
        member.setCreatedAt(LocalDateTime.now()); 
        
        // Repository를 사용해 DB에 저장 (데이터 그릇에 담기)
        Member savedMember = memberRepository.save(member);
        
        // 저장된 정보를 클라이언트에게 반환
        return ResponseEntity.ok(savedMember);
    }

    /**
     * [API 2] 모든 회원 목록 조회 (데이터 조회 - GET)
     * 주소: GET /api/members
     * @return DB에 저장된 모든 회원 목록
     */
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        // Repository를 사용해 DB의 모든 Member를 조회
        List<Member> members = memberRepository.findAll();
        
        // 조회된 목록을 클라이언트에게 반환
        return ResponseEntity.ok(members);
    }
}