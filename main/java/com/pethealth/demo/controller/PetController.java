package com.pethealth.demo.controller;

import com.pethealth.demo.domain.Pet;
import com.pethealth.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // ⭐️ 공통 주소를 /api로 변경 (더 유연하게)
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * [API 3] 특정 회원의 펫 등록하기
     * 주소: POST /api/members/{userId}/pets
     * {userId} 부분은 실제 요청 시 ID 숫자로 바뀜 (예: /api/members/1/pets)
     */
    @PostMapping("/members/{userId}/pets")
    public ResponseEntity<?> createPet(
            @PathVariable Long userId, // 1. ⭐️ 주소(URL)에서 {userId} 값을 가져옴
            @RequestBody Pet pet       // 2. ⭐️ 요청 Body에서 펫 정보(JSON)를 가져옴
    ) {
        try {
            // 3. PetService에 펫 정보와 주인 ID를 넘겨서 등록
            Pet savedPet = petService.registerPet(pet, userId);
            
            // 4. 성공 시: 저장된 펫 정보 반환
            return ResponseEntity.ok(savedPet);

        } catch (IllegalArgumentException e) {
            // 5. ⭐️ Service에서 "존재하지 않는 회원" 예외가 발생하면 에러 반환
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}