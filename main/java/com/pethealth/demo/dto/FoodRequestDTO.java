package com.pethealth.demo.dto;

import lombok.Getter;

// 사료 등록 요청(JSON)을 담을 그릇
@Getter
public class FoodRequestDTO {
    
    private String foodName;
    private String manufacturer; // 제조사
    private String foodType;     // 사료 타입 (예: 건식, 습식)
    private String speciesTarget; // 대상 동물 (예: 강아지, 고양이)
}