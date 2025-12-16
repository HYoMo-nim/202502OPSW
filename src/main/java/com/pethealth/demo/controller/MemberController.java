package com.pethealth.demo.controller;

import com.pethealth.demo.domain.Member;
import com.pethealth.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    // ⭐️ MemberRepository 대신 MemberService를 주입받음
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { // ⭐️ 주입 대상을 Service로 변경
        this.memberService = memberService;
    }

    /**
     * [API 1] 회원 가입 (POST /api/members) 이제 이 메서드는 '창구' 역할만 합니다.
     */
    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody Member member) {
        try {
            // 1. 실제 업무는 Service에게 시킴
            Member savedMember = memberService.registerUser(member);

            // 2. 성공 시: 저장된 회원 정보 반환
            return ResponseEntity.ok(savedMember);

        } catch (IllegalArgumentException e) {
            // 3. Service에서 "이미 사용 중인 이메일" 예외가 발생하면
            //    클라이언트에게 400 Bad Request(잘못된 요청) 에러와 메시지 반환
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    /**
     * [API 2] 모든 회원 목록 조회 (GET /api/members)
     */
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        // 이 기능도 Service를 통해 호출
        List<Member> members = memberService.findAllMembers();
        return ResponseEntity.ok(members);
    }
}
