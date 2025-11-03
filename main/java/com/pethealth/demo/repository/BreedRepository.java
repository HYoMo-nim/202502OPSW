package com.pethealth.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pethealth.demo.domain.Breed;

public interface BreedRepository extends JpaRepository<Breed, Long> {
}