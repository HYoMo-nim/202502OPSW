package com.pethealth.demo.domain;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable // 이 클래스가 다른 엔티티에 포함될 수 있음을 선언
public class FoodIngredientId implements Serializable {

    @Column(name = "food_id")
    private Long foodId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    // --- (JPA가 복합 키를 비교하기 위해 필수) ---

    // 기본 생성자
    public FoodIngredientId() {}

    // 필드 생성자
    public FoodIngredientId(Long foodId, Long ingredientId) {
        this.foodId = foodId;
        this.ingredientId = ingredientId;
    }

    // equals() : 두 객체의 내용이 같은지 비교
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodIngredientId that = (FoodIngredientId) o;
        return Objects.equals(foodId, that.foodId) &&
               Objects.equals(ingredientId, that.ingredientId);
    }

    // hashCode() : 객체의 고유 식별자(해시코드) 생성
    @Override
    public int hashCode() {
        return Objects.hash(foodId, ingredientId);
    }
}