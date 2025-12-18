package com.pethealth.demo.domain;
import java.io.Serializable;
import java.util.Objects;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Data
@Embeddable // 이 클래스가 다른 엔티티에 포함될 수 있음
public class PetAllergyId implements Serializable {

    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    // 기본 생성자
    public PetAllergyId() {}

    // 필드 생성자
    public PetAllergyId(Long petId, Long ingredientId) {
        this.petId = petId;
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetAllergyId that = (PetAllergyId) o;
        return Objects.equals(petId, that.petId) &&
               Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId, ingredientId);
    }
}