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
    private final MemberRepository memberRepository; // ⭐️ 주인을 찾기 위해 MemberRepository도 필요

    @Autowired
    public PetService(PetRepository petRepository, MemberRepository memberRepository) {
        this.petRepository = petRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * 펫 등록 로직
     * @param pet 클라이언트가 보낸 펫 정보
     * @param userId 펫의 주인이 될 회원의 ID
     * @return 저장된 펫 정보
     */
    @Transactional
    public Pet registerPet(Pet pet, Long userId) {
        // 1. ⭐️ userId를 이용해 DB에서 Member(주인)를 찾음
        Member owner = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 ID입니다: " + userId));

        // 2. ⭐️ 찾은 Member 객체를 Pet 객체에 연결
        //    (Pet.java의 private Member member; 필드에 값을 설정)
        pet.setMember(owner);

        // 3. 펫 정보를 DB에 저장
        return petRepository.save(pet);
    }
}