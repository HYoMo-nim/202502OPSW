package com.pethealth.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

    private static final String API_KEY = "AIzaSyC8vN8CL-1kPH_HHxJgHRNtI4nl2gsfbFY"; 
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + API_KEY;

    public String getRecommendation(String petInfo, String foodList) {
        // 1. 제미나이에게 보낼 프롬프트(질문) 만들기
        String prompt = "반려동물 정보: " + petInfo + "\n" +
                        "사료 목록: " + foodList + "\n" +
                        "이 반려동물에게 가장 적합한 사료 1개를 추천해주고, 그 이유를 한국어로 3줄 이내로 설명해줘.";

        // 2. 요청 본문(Body) 만들기 (JSON 구조)
        Map<String, Object> content = new HashMap<>();
        Map<String, String> parts = new HashMap<>();
        parts.put("text", prompt);
        content.put("parts", new Map[]{parts});

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", new Map[]{content});

        // 3. API 호출 (HTTP 요청 보내기)
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(API_URL, request, String.class);
            return response.getBody(); // 제미나이의 대답 반환
        } catch (Exception e) {
            e.printStackTrace();
            return "오류가 발생했습니다: " + e.getMessage();
        }
    }
}