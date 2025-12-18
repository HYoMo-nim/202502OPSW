package com.pethealth.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pethealth.demo.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}