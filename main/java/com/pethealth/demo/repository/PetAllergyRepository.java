package com.pethealth.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pethealth.demo.domain.PetAllergy;
import com.pethealth.demo.domain.PetAllergyId;

// JpaRepository<관리할 Entity, 그 Entity의 ID 타입>
public interface PetAllergyRepository extends JpaRepository<PetAllergy, PetAllergyId> {
}