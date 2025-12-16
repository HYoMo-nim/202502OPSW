package com.pethealth.demo.service;

import com.pethealth.demo.domain.Breed;
import com.pethealth.demo.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 이 클래스가 Spring의 "서비스"임을 선언
public class BreedService {

    private final BreedRepository breedRepository;

    @Autowired
    public BreedService(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    /**
     * 모든 견종/묘종 목록을 조회하는 로직
     * @return DB에 있는 모든 Breed 리스트
     */
    public List<Breed> findAllBreeds() {
        // Repository의 findAll() 메서드를 호출해 모든 데이터를 가져옴
        return breedRepository.findAll();
    }
}