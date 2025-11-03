package com.pethealth.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable // 이 클래스가 다른 엔티티에 포함될 수 있음
public class PetAllergyId implements Serializable {

    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    // --- (JPA가 복합 키를 비교하기 위해 필수) ---

    // 기본 생성자
    public PetAllergyId() {}

    // 필드 생성자
    public PetAllergyId(Long petId, Long ingredientId) {
        this.petId = petId;
        this.ingredientId = ingredientId;
    }

    // equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetAllergyId that = (PetAllergyId) o;
        return Objects.equals(petId, that.petId) &&
               Objects.equals(ingredientId, that.ingredientId);
    }

    // hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(petId, ingredientId);
    }

    // (Getter/Setter는 Lombok을 쓰지 않으므로 생략, 
    //  혹은 필요시 추가해도 됩니다)
}