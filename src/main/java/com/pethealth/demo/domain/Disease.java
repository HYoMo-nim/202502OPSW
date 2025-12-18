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
import lombok.Data;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.ManyToMany;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "disease")
public class Disease {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "disease_disease_id_seq"
    )
    @SequenceGenerator(
        name = "disease_disease_id_seq",
        sequenceName = "disease_disease_id_seq",
        allocationSize = 1
    )
    @Column(name = "disease_id")
    private Long diseaseId;

    @Column(nullable = false)
    private String diseaseName;

    private String description;

    @Column(name = "is_genetic")
    private boolean isGenetic;
   
    @ManyToMany(mappedBy = "diseases") // 'Breed' 엔티티의 'diseases' 필드에 의해 매핑됨
    private Set<Breed> breeds = new HashSet<>();
}