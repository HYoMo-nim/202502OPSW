package com.pethealth.demo.controller;

import com.pethealth.demo.domain.Breed;
import com.pethealth.demo.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api") // /api로 시작하는 공통 주소
public class BreedController {

    private final BreedService breedService;

    @Autowired
    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    /**
     * [API 4] 모든 견종/묘종 목록 조회
     * 주소: GET /api/breeds
     */
    @GetMapping("/breeds")
    public ResponseEntity<List<Breed>> getAllBreeds() {
        // 1. Service를 통해 모든 Breed 데이터를 가져옴
        List<Breed> breeds = breedService.findAllBreeds();
        
        // 2. 성공 응답(200 OK)과 함께 데이터 목록을 반환
        return ResponseEntity.ok(breeds);
    }
}