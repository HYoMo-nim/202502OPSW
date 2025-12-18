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

    @GetMapping("/recommend")
    public String recommendFood(@RequestParam String pet) {
        String foodList = "1. 로얄캐닌(닭고기 함유), 2. 네추럴코어(연어 베이스), 3. ANF(오리고기)";
        return geminiService.getRecommendation(pet, foodList);
    }
}