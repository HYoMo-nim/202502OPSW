package com.pethealth.demo.controller;

import com.pethealth.demo.service.GeminiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RecommendationController {

    private final GeminiService geminiService;

    // 생성자 주입
    public RecommendationController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    // 예: http://localhost:8080/recommend?pet=말티즈,5kg,닭고기알러지
    @GetMapping("/recommend")
    public String recommendFood(@RequestParam String pet) {
        // 실제로는 DB에서 사료 목록을 가져와야 하지만, 지금은 임시 데이터를 씁니다.
        String foodList = "1. 로얄캐닌(닭고기 함유), 2. 네추럴코어(연어 베이스), 3. ANF(오리고기)";
        // 제미나이에게 물어보기
        return geminiService.getRecommendation(pet, foodList);
    }
}