package com.pethealth.demo.service;

import com.pethealth.demo.domain.Food;
import com.pethealth.demo.domain.FoodIngredient;
import com.pethealth.demo.dto.FoodRequestDTO;
import com.pethealth.demo.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Transactional
    public Food registerFood(FoodRequestDTO requestDTO) {
        // 1. DTO를 Food 엔티티로 변환
        Food newFood = new Food();
        newFood.setFoodName(requestDTO.getFoodName());
        newFood.setManufacturer(requestDTO.getManufacturer());
        newFood.setFoodType(requestDTO.getFoodType());
        newFood.setSpeciesTarget(requestDTO.getSpeciesTarget());

        // 2. DB에 저장
        return foodRepository.save(newFood);
    }

    @Transactional(readOnly = true)
    public Set<FoodIngredient> findIngredientsByFoodId(Long foodId) {
        // ID로 Food(사료)를 찾음
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사료 ID입니다: " + foodId));
        return food.getIngredients();
    }
}