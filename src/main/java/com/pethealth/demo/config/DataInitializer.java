package com.pethealth.demo.config;

import com.pethealth.demo.domain.*;
import com.pethealth.demo.repository.*;
import com.pethealth.demo.util.CsvUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

@Configuration
public class DataInitializer {

    private final MemberRepository memberRepository;
    private final BreedRepository breedRepository;
    private final DiseaseRepository diseaseRepository;
    private final IngredientRepository ingredientRepository;
    private final FoodRepository foodRepository;
    private final PetRepository petRepository;
    private final PetAllergyRepository petAllergyRepository;
    private final FoodIngredientRepository foodIngredientRepository;
    private final JdbcTemplate jdbcTemplate;

    public DataInitializer(MemberRepository memberRepository, BreedRepository breedRepository,
            DiseaseRepository diseaseRepository, IngredientRepository ingredientRepository,
            FoodRepository foodRepository, PetRepository petRepository,
            PetAllergyRepository petAllergyRepository, FoodIngredientRepository foodIngredientRepository,
            JdbcTemplate jdbcTemplate) {
        this.memberRepository = memberRepository;
        this.breedRepository = breedRepository;
        this.diseaseRepository = diseaseRepository;
        this.ingredientRepository = ingredientRepository;
        this.foodRepository = foodRepository;
        this.petRepository = petRepository;
        this.petAllergyRepository = petAllergyRepository;
        this.foodIngredientRepository = foodIngredientRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (memberRepository.count() == 0) {
                System.out.println("========== [데이터 로드 시작] ==========");

                // 1. 기초 데이터 로드
                loadMembers("data/cat_menber.csv");
                loadMembers("data/dog_menber.csv");

                loadBreeds("data/cat_breed.csv", "고양이");
                loadBreeds("data/dog_breed.csv", "강아지");

                loadDiseases("data/cat_disease.csv");
                loadDiseases("data/dog_disease.csv");

                loadIngredients("data/cat_ingredient.csv");
                loadIngredients("data/dog_ingredient.csv");

                loadFoods("data/cat_food.csv");
                loadFoods("data/dog_food.csv");

                // 2. 관계 데이터 로드 (FK 의존성)
                loadBreedDiseases("data/cat_breed_disease.csv");
                loadBreedDiseases("data/dog_breed_disease.csv");

                loadPets("data/cat_pet.csv");
                loadPets("data/dog_pet.csv");

                loadFoodIngredients("data/cat_food_ingredient.csv");
                loadFoodIngredients("data/dog_food_ingredient.csv");

                loadPetAllergies("data/cat_pet_allergy.csv");
                loadPetAllergies("data/dog_pet_allergy.csv");

                // 3. 시퀀스 동기화
                resetSequences();

                System.out.println("========== [데이터 로드 완료] ==========");
            }
        };
    }

    // 파일 읽기 헬퍼
    private void processCsv(String path, java.util.function.Consumer<String[]> processor) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            if (!resource.exists()) {
                System.out.println("파일 없음 (스킵): " + path);
                return;
            }
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            br.readLine(); // 헤더 건너뛰기
            String line;
            while ((line = br.readLine()) != null) {
                // 빈 줄이나 쉼표만 있는 줄 건너뛰기
                if (line.trim().isEmpty() || line.replace(",", "").trim().isEmpty()) {
                    continue;
                }

                String[] data = CsvUtils.parseLine(line);
                try {
                    processor.accept(data);
                } catch (Exception e) {
                    System.err.println("데이터 오류 (" + path + "): " + line + " -> " + e.getMessage());
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void loadMembers(String path) {
        processCsv(path, data -> {
            // [0]user_id , [1]email, [2]password, [3]nickname, [4]created_at
            Long id = Long.parseLong(data[0]);
            if (!memberRepository.existsById(id)) {
                Member m = new Member();
                m.setUserId(id);
                m.setEmail(data[1]);
                m.setPassword(data[2]);
                m.setNickname(data[3]);
                m.setCreatedAt(CsvUtils.parseDateTime(data[4]));
                memberRepository.save(m);
            }
        });
    }

    @Transactional
    public void loadBreeds(String path, String defaultSpecies) {
        processCsv(path, data -> {
            // [0]breed_id, [1]breed_name, [2]species
            Long id = Long.parseLong(data[0]);
            if (!breedRepository.existsById(id)) {
                Breed b = new Breed();
                b.setBreedId(id);
                b.setBreedName(data[1]);
                b.setSpecies(data.length > 2 && !data[2].isBlank() ? data[2] : defaultSpecies);
                breedRepository.save(b);
            }
        });
    }

    @Transactional
    public void loadDiseases(String path) {
        processCsv(path, data -> {
            // [0]disease_id, [1]disease_name, [2]description, [3]is_genetic(TRUE/FALSE)
            Long id = Long.parseLong(data[0]);
            if (!diseaseRepository.existsById(id)) {
                Disease d = new Disease();
                d.setDiseaseId(id);
                d.setDiseaseName(data[1]);
                d.setDescription(data[2]);
                d.setGenetic(Boolean.parseBoolean(data[3]));
                diseaseRepository.save(d);
            }
        });
    }

    @Transactional
    public void loadIngredients(String path) {
        processCsv(path, data -> {
            // [0]ingredient_id, [1]ingredient_name, [2]is_known_allergen
            Long id = Long.parseLong(data[0]);
            if (!ingredientRepository.existsById(id)) {
                Ingredient i = new Ingredient();
                i.setIngredientId(id);
                i.setIngredientName(data[1]);
                i.setKnownAllergen(Boolean.parseBoolean(data[2]));
                ingredientRepository.save(i);
            }
        });
    }

    @Transactional
    public void loadFoods(String path) {
        processCsv(path, data -> {
            // [0]food_id, [1]food_name, [2]manufacturer, [3]food_type, [4]species_target
            Long id = Long.parseLong(data[0]);
            if (!foodRepository.existsById(id)) {
                Food f = new Food();
                f.setFoodId(id);
                f.setFoodName(data[1]);
                f.setManufacturer(data[2]);
                f.setFoodType(data[3]);
                f.setSpeciesTarget(data[4]);
                foodRepository.save(f);
            }
        });
    }

    @Transactional
    public void loadPets(String path) {
        processCsv(path, data -> {
            // [0]pet_id, [1]user_id, [2]breed_id, [3]name, [4]breed(중복텍스트), [5]gender,
            // [6]birth_date, [7]weight_kg
            Long id = Long.parseLong(data[0]);
            if (!petRepository.existsById(id)) {
                Pet p = new Pet();
                p.setPetId(id);

                Member m = new Member();
                m.setUserId(Long.parseLong(data[1]));
                p.setMember(m);

                Breed b = new Breed();
                b.setBreedId(Long.parseLong(data[2]));
                p.setBreed(b);

                p.setName(data[3]);
                p.setGender(data[5]);
                p.setBirthDate(CsvUtils.parseDate(data[6]));
                p.setWeightKg(new BigDecimal(data[7]));

                petRepository.save(p);
            }
        });
    }

    @Transactional
    public void loadFoodIngredients(String path) {
        processCsv(path, data -> {
            Long foodId = Long.parseLong(data[0]);
            Long ingId = Long.parseLong(data[1]);

            FoodIngredient fi = new FoodIngredient();
            FoodIngredientId id = new FoodIngredientId(foodId, ingId);
            fi.setId(id);

            Food f = new Food();
            f.setFoodId(foodId);
            fi.setFood(f);

            Ingredient i = new Ingredient();
            i.setIngredientId(ingId);
            fi.setIngredient(i);

            fi.setIngredientOrder(Integer.parseInt(data[2]));
            foodIngredientRepository.save(fi);
        });
    }

    @Transactional
    public void loadPetAllergies(String path) {
        processCsv(path, data -> {
            // [0]pet_id, [1]ingredient_id, [2]reaction_type
            Long petId = Long.parseLong(data[0]);
            Long ingId = Long.parseLong(data[1]);

            PetAllergy pa = new PetAllergy();
            PetAllergyId id = new PetAllergyId(petId, ingId);
            pa.setId(id);

            Pet p = new Pet();
            p.setPetId(petId);
            pa.setPet(p);

            Ingredient i = new Ingredient();
            i.setIngredientId(ingId);
            pa.setIngredient(i);

            pa.setReactionType(CsvUtils.removeQuotes(data[2]));
            petAllergyRepository.save(pa);
        });
    }

    @Transactional
    public void loadBreedDiseases(String path) {
        processCsv(path, data -> {
            // [0]breed_id, [1]disease_id
            Long breedId = Long.parseLong(data[0]);
            Long diseaseId = Long.parseLong(data[1]);

            Breed breed = breedRepository.findById(breedId).orElse(null);
            Disease disease = diseaseRepository.findById(diseaseId).orElse(null);

            if (breed != null && disease != null) {
                // Breed 엔티티에 @ManyToMany Set<Disease> diseases 필드가 있다고 가정
                breed.getDiseases().add(disease);
                breedRepository.save(breed);
            }
        });
    }

    private void resetSequences() {
        System.out.println("시퀀스 값 재설정 중...");
        resetSeq("member", "user_id", "member_user_id_seq");
        resetSeq("breed", "breed_id", "breed_breed_id_seq");
        resetSeq("disease", "disease_id", "disease_disease_id_seq");
        resetSeq("food", "food_id", "food_food_id_seq");
        resetSeq("ingredient", "ingredient_id", "ingredient_ingredient_id_seq");
        resetSeq("pet", "pet_id", "pet_pet_id_seq");
    }

    private void resetSeq(String table, String col, String seq) {
        try {
            String sql = String.format("SELECT setval('%s', COALESCE((SELECT MAX(%s) FROM %s), 1))", seq, col, table);
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            System.err.println("시퀀스 초기화 실패 (" + seq + "): " + e.getMessage());
        }
    }
}
