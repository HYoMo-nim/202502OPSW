package com.pethealth.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pethealth.demo.domain.FoodIngredient;
import com.pethealth.demo.domain.FoodIngredientId;

// JpaRepository<관리할 Entity, 그 Entity의 ID 타입>
public interface FoodIngredientRepository extends JpaRepository<FoodIngredient, FoodIngredientId> {
}