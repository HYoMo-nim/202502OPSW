package com.pethealth.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pethealth.demo.domain.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}