package com.pethealth.demo.domain;
import com.pethealth.demo.domain.Member;

import java.math.BigDecimal; // numeric(5,2) 타입은 BigDecimal이 적합
import java.time.LocalDate; // date 타입은 LocalDate

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "pet_pet_id_seq"
    )
    @SequenceGenerator(
        name = "pet_pet_id_seq",
        sequenceName = "pet_pet_id_seq",
        allocationSize = 1
    )
    @Column(name = "pet_id")
    private Long petId;

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    private String gender;

    @Column(name = "weight_kg")
    private BigDecimal weightKg;

    @Column(name = "profile_img_url")
    private String profileImgUrl;

    // --- 관계 매핑 (Foreign Key) ---
    // 'pet' 테이블의 'user_id' 컬럼
    
    @ManyToOne(fetch = FetchType.LAZY) // Pet(Many) to Member(One)
    @JoinColumn(name = "user_id") // DB의 외래 키 컬럼 이름
    private Member member; // 'Member' 객체 자체를 참조

    /**
     * N:1 관계 (Pet이 관계의 주인)
     * 이 Pet의 견종/묘종 정보
     */
    @ManyToOne(fetch = FetchType.LAZY) // Pet(Many) to Breed(One)
    @JoinColumn(name = "breed_id") // DB의 외래 키(FK) 컬럼 이름
    private Breed breed; // Breed 객체 자체를 참조
    
    /**
     * 1:N 관계 (PetAllergy 테이블과 연결)
     * 이 Pet이 가진 알러지 목록 (중간 엔티티 PetAllergy를 통해)
     */
    @OneToMany(mappedBy = "pet") // 'PetAllergy' 엔티티의 'pet' 필드와 연결
    private Set<PetAllergy> allergies = new HashSet<>();
}