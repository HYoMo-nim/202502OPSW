package com.pethealth.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food_ingredient")
public class FoodIngredient {

    // 1. 복합 키를 ID로 사용
    @EmbeddedId
    private FoodIngredientId id;

    // 2. 관계 1: 'Food' 엔티티와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("foodId") // 'id' 필드(FoodIngredientId)의 'foodId' 속성과 매핑
    @JoinColumn(name = "food_id")
    private Food food;

    // 3. 관계 2: 'Ingredient' 엔티티와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId") // 'id' 필드(FoodIngredientId)의 'ingredientId' 속성과 매핑
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    
    // 4. 추가 컬럼
    @Column(name = "ingredient_order")
    private Integer ingredientOrder;
}