package com.pethealth.demo.domain;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class FoodIngredientId implements Serializable {

    @Column(name = "food_id")
    private Long foodId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    // 기본 생성자
    public FoodIngredientId() {}

    // 필드 생성자
    public FoodIngredientId(Long foodId, Long ingredientId) {
        this.foodId = foodId;
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodIngredientId that = (FoodIngredientId) o;
        return Objects.equals(foodId, that.foodId) &&
               Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodId, ingredientId);
    }
}