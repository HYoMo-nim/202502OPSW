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
    
    /**
     * 특정 사료(Food)의 모든 성분(Ingredient)을 조회하는 로직
     * @param foodId 조회할 사료의 ID
     * @return 해당 사료의 성분 목록 (FoodIngredient Set)
     */
    @Transactional(readOnly = true) // ⭐️ LAZY 로딩된 ingredients를 조회하기 위해 필요
    public Set<FoodIngredient> findIngredientsByFoodId(Long foodId) {
        // 1. ID로 Food(사료)를 찾음
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사료 ID입니다: " + foodId));

        // 2. ⭐️ @Transactional 덕분에, LAZY 로딩된 ingredients에 접근 가능
        //    (Food.java에 정의된 'ingredients' 필드를 그대로 반환)
        //    (이 Set에는 Ingredient 객체와 'ingredientOrder'가 모두 포함됨)
        return food.getIngredients();
    }
}