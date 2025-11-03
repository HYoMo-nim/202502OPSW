package com.pethealth.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "breed")
public class Breed {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "breed_breed_id_seq" // schema.sql의 시퀀스 이름
    )
    @SequenceGenerator(
        name = "breed_breed_id_seq",
        sequenceName = "breed_breed_id_seq",
        allocationSize = 1
    )
    @Column(name = "breed_id")
    private Long breedId;

    @Column(nullable = false)
    private String breedName;

    @Column(nullable = false)
    private String species;
    
    /**
     * 다대다(N:N) 관계 설정 (Breed가 관계의 주인)
     * 이 Breed(견종)와 연관된 Disease(질병) 목록
     */
    @ManyToMany(fetch = FetchType.LAZY) // 지연 로딩
    @JoinTable(
        name = "breed_disease", // 1. 연결할 중간 테이블 이름
        joinColumns = @JoinColumn(name = "breed_id"), // 2. 중간 테이블에서 '나(Breed)'를 참조하는 FK
        inverseJoinColumns = @JoinColumn(name = "disease_id") // 3. 중간 테이블에서 '상대방(Disease)'을 참조하는 FK
    )
    
    private Set<Disease> diseases = new HashSet<>(); // 중복을 피하기 위해 Set 사용
    // (참고) Pet.java와의 관계 (1:N)
    // 1마리의 Breed는 여러 마리의 Pet을 가질 수 있습니다.
    
    @OneToMany(mappedBy = "breed")
    private List<Pet> pets = new ArrayList<>();
}