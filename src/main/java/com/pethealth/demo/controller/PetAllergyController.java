package com.pethealth.demo.controller;

import com.pethealth.demo.domain.PetAllergy;
import com.pethealth.demo.dto.PetAllergyRequestDTO;
import com.pethealth.demo.service.PetAllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PetAllergyController {

    private final PetAllergyService petAllergyService;

    @Autowired
    public PetAllergyController(PetAllergyService petAllergyService) {
        this.petAllergyService = petAllergyService;
    }

    @PostMapping("/pets/{petId}/allergies")
    public ResponseEntity<?> addPetAllergy(
            @PathVariable Long petId, // 1. 주소(URL)에서 {petId} 값을 가져옴
            @RequestBody PetAllergyRequestDTO requestDTO // 2. 요청 Body에서 DTO를 가져옴
    ) {
        try {
            // 3. Service를 호출해 알러지 정보 등록
            PetAllergy savedAllergy = petAllergyService.addAllergy(petId, requestDTO);
            
            // 4. 성공 시: 저장된 PetAllergy 객체 반환
            return ResponseEntity.ok(savedAllergy);

        } catch (IllegalArgumentException e) {
            // 5. Service에서 발생한 모든 예외(존재하지 않는 ID, 중복 등) 처리
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}