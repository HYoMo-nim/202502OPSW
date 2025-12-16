package com.pethealth.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pethealth.demo.domain.Disease;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}