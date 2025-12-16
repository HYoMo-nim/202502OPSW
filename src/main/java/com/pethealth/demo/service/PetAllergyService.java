package com.pethealth.demo.service;

import com.pethealth.demo.domain.Ingredient;
import com.pethealth.demo.domain.Pet;
import com.pethealth.demo.domain.PetAllergy;
import com.pethealth.demo.domain.PetAllergyId;
import com.pethealth.demo.dto.PetAllergyRequestDTO;
import com.pethealth.demo.repository.IngredientRepository;
import com.pethealth.demo.repository.PetAllergyRepository;
import com.pethealth.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetAllergyService {

    private final PetRepository petRepository;
    private final IngredientRepository ingredientRepository;
    private final PetAllergyRepository petAllergyRepository;

    @Autowired
    public PetAllergyService(PetRepository petRepository, 
                             IngredientRepository ingredientRepository, 
                             PetAllergyRepository petAllergyRepository) {
        this.petRepository = petRepository;
        this.ingredientRepository = ingredientRepository;
        this.petAllergyRepository = petAllergyRepository;
    }

    /**
     * 특정 펫에게 알러지 정보를 추가하는 로직
     * @param petId 알러지를 추가할 펫의 ID
     * @param requestDTO 클라이언트가 보낸 알러지 정보 (성분 ID, 반응)
     * @return 저장된 PetAllergy 정보
     */
    @Transactional
    public PetAllergy addAllergy(Long petId, PetAllergyRequestDTO requestDTO) {
        
        // 1. Pet 엔티티를 DB에서 찾음
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 펫 ID입니다: " + petId));

        // 2. Ingredient 엔티티를 DB에서 찾음
        Ingredient ingredient = ingredientRepository.findById(requestDTO.getIngredientId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 성분 ID입니다: " + requestDTO.getIngredientId()));

        // 3. 복합 키(PetAllergyId) 생성
        PetAllergyId allergyId = new PetAllergyId(petId, requestDTO.getIngredientId());

        // (선택) 4. 이미 등록된 알러지인지 확인
        if(petAllergyRepository.existsById(allergyId)) {
            throw new IllegalArgumentException("이미 등록된 알러지 정보입니다.");
        }
        
        // 5. 새로운 PetAllergy 엔티티 생성
        PetAllergy newAllergy = new PetAllergy();
        newAllergy.setId(allergyId);        // 복합 키 설정
        newAllergy.setPet(pet);             // Pet 객체 연결
        newAllergy.setIngredient(ingredient); // Ingredient 객체 연결
        newAllergy.setReactionType(requestDTO.getReactionType()); // 추가 정보(반응) 설정

        // 6. DB에 저장
        return petAllergyRepository.save(newAllergy);
    }
}