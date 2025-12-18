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
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "ingredient_ingredient_id_seq"
    )
    @SequenceGenerator(
        name = "ingredient_ingredient_id_seq",
        sequenceName = "ingredient_ingredient_id_seq",
        allocationSize = 1
    )
    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Column(nullable = false, unique = true)
    private String ingredientName;

    @Column(name = "is_known_allergen")
    private boolean isKnownAllergen;

    @OneToMany(mappedBy = "ingredient") // 'FoodIngredient' 엔티티의 'ingredient' 필드와 연결
    private Set<FoodIngredient> foods = new HashSet<>();
    
    @OneToMany(mappedBy = "ingredient") // 'PetAllergy' 엔티티의 'ingredient' 필드와 연결
    private Set<PetAllergy> allergyPets = new HashSet<>();
  }