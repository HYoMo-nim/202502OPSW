package com.pethealth.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pethealth.demo.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
    // 필요한 쿼리 메소드를 추가할 수 있습니다.
}