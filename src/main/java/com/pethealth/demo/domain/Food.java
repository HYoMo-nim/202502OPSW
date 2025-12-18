package com.pethealth.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "food_food_id_seq"
    )
    @SequenceGenerator(
        name = "food_food_id_seq",
        sequenceName = "food_food_id_seq",
        allocationSize = 1
    )
    @Column(name = "food_id")
    private Long foodId;

    @Column(nullable = false)
    private String foodName;

    private String manufacturer;

    @Column(nullable = false)
    private String foodType;

    private String speciesTarget;

    @OneToMany(mappedBy = "food") // 'FoodIngredient' 엔티티의 'food' 필드와 연결
    private Set<FoodIngredient> ingredients = new HashSet<>();
}