package com.pethealth.demo.dto;

import lombok.Getter;

// 클라이언트의 요청(JSON)을 담을 그릇
@Getter
public class PetAllergyRequestDTO {
    
    // 1. 알러지를 일으키는 성분(Ingredient)의 ID
    private Long ingredientId;
    
    // 2. 알러지 반응 타입 (예: "가려움", "설사")
    private String reactionType;
}