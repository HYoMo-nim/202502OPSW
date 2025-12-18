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

    public List<Breed> findAllBreeds() {
        return breedRepository.findAll();
    }
}