package com.pethealth.demo.service;

import com.pethealth.demo.domain.Member;
import com.pethealth.demo.domain.Pet;
import com.pethealth.demo.repository.MemberRepository;
import com.pethealth.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 1. 이 클래스가 Spring의 "서비스"임을 선언
public class PetService {

    private final PetRepository petRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public PetService(PetRepository petRepository, MemberRepository memberRepository) {
        this.petRepository = petRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Pet registerPet(Pet pet, Long userId) {
        // 1. userId를 이용해 DB에서 Member(주인)를 찾음
        Member owner = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 ID입니다: " + userId));

        // 2. 찾은 Member 객체를 Pet 객체에 연결
        //    (Pet.java의 private Member member; 필드에 값을 설정)
        pet.setMember(owner);

        // 3. 펫 정보를 DB에 저장
        return petRepository.save(pet);
    }
}