package com.pethealth.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pethealth.demo.domain.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
}