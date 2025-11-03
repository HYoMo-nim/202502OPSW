package com.pethealth.demo.controller;

import com.pethealth.demo.domain.Food;
import com.pethealth.demo.domain.FoodIngredient;
import com.pethealth.demo.dto.FoodRequestDTO;
import com.pethealth.demo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api") // /api로 시작하는 공통 주소
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }
    
    /**
     * [API 7] 새로운 사료 등록
     * 주소: POST /api/foods
     */
    @PostMapping("/foods")
    public ResponseEntity<Food> createFood(@RequestBody FoodRequestDTO requestDTO) {
        // Service를 호출해 사료 등록
        Food savedFood = foodService.registerFood(requestDTO);
        
        // 성공 응답(200 OK)과 함께 저장된 Food 객체 반환
        return ResponseEntity.ok(savedFood);
    }
    
    /**
     * [API 5] 특정 사료(Food)의 성분(Ingredient) 목록 조회
     * 주소: GET /api/foods/{foodId}/ingredients
     */
    @GetMapping("/foods/{foodId}/ingredients")
    public ResponseEntity<?> getFoodIngredients(@PathVariable Long foodId) {
        try {
            // 1. Service를 통해 사료의 성분 목록을 조회
            Set<FoodIngredient> ingredients = foodService.findIngredientsByFoodId(foodId);
            
            // 2. 성공 응답(200 OK)과 함께 성분 목록 반환
            return ResponseEntity.ok(ingredients);
            
        } catch (IllegalArgumentException e) {
            // 3. Service에서 "존재하지 않는 사료" 예외가 발생하면 에러 반환
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}