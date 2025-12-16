CREATE TABLE public.breed (
    breed_id bigint NOT NULL,
    breed_name character varying(100) NOT NULL,
    species character varying(20) NOT NULL
);


CREATE SEQUENCE public.breed_breed_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


 SEQUENCE public.breed_breed_id_seq OWNED BY public.breed.breed_id;


CREATE TABLE public.breed_disease (
    breed_id bigint NOT NULL,
    disease_id bigint NOT NULL
);


CREATE TABLE public.disease (
    disease_id bigint NOT NULL,
    disease_name character varying(255) NOT NULL,
    description text,
    is_genetic boolean DEFAULT false
);


CREATE SEQUENCE public.disease_disease_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.disease_disease_id_seq OWNED BY public.disease.disease_id;


CREATE TABLE public.food (
    food_id bigint NOT NULL,
    food_name character varying(255) NOT NULL,
    manufacturer character varying(100),
    food_type character varying(20) NOT NULL,
    species_target character varying(20)
);


CREATE SEQUENCE public.food_food_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.food_food_id_seq OWNED BY public.food.food_id;


CREATE TABLE public.food_ingredient (
    food_id bigint NOT NULL,
    ingredient_id bigint NOT NULL,
    ingredient_order integer
);


CREATE TABLE public.ingredient (
    ingredient_id bigint NOT NULL,
    ingredient_name character varying(100) NOT NULL,
    is_known_allergen boolean DEFAULT false
);


CREATE SEQUENCE public.ingredient_ingredient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ingredient_ingredient_id_seq OWNED BY public.ingredient.ingredient_id;


CREATE TABLE public.member (
    user_id bigint NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    nickname character varying(50),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


CREATE SEQUENCE public.member_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.member_user_id_seq OWNED BY public.member.user_id;


CREATE TABLE public.pet (
    pet_id bigint NOT NULL,
    user_id bigint NOT NULL,
    breed_id bigint,
    name character varying(50) NOT NULL,
    birth_date date,
    gender character varying(20),
    weight_kg numeric(5,2),
    profile_img_url character varying(255)
);


CREATE TABLE public.pet_allergy (
    pet_id bigint NOT NULL,
    ingredient_id bigint NOT NULL,
    reaction_type character varying(100)
);

CREATE SEQUENCE public.pet_pet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pet_pet_id_seq OWNED BY public.pet.pet_id;


ALTER TABLE ONLY public.breed ALTER COLUMN breed_id SET DEFAULT nextval('public.breed_breed_id_seq'::regclass);


ALTER TABLE ONLY public.disease ALTER COLUMN disease_id SET DEFAULT nextval('public.disease_disease_id_seq'::regclass);


ALTER TABLE ONLY public.food ALTER COLUMN food_id SET DEFAULT nextval('public.food_food_id_seq'::regclass);


ALTER TABLE ONLY public.ingredient ALTER COLUMN ingredient_id SET DEFAULT nextval('public.ingredient_ingredient_id_seq'::regclass);


ALTER TABLE ONLY public.member ALTER COLUMN user_id SET DEFAULT nextval('public.member_user_id_seq'::regclass);


ALTER TABLE ONLY public.pet ALTER COLUMN pet_id SET DEFAULT nextval('public.pet_pet_id_seq'::regclass);


ALTER TABLE ONLY public.breed_disease
    ADD CONSTRAINT breed_disease_pkey PRIMARY KEY (breed_id, disease_id);


ALTER TABLE ONLY public.breed
    ADD CONSTRAINT breed_pkey PRIMARY KEY (breed_id);


ALTER TABLE ONLY public.disease
    ADD CONSTRAINT disease_pkey PRIMARY KEY (disease_id);


ALTER TABLE ONLY public.food_ingredient
    ADD CONSTRAINT food_ingredient_pkey PRIMARY KEY (food_id, ingredient_id);


ALTER TABLE ONLY public.food
    ADD CONSTRAINT food_pkey PRIMARY KEY (food_id);


ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_ingredient_name_key UNIQUE (ingredient_name);


ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_pkey PRIMARY KEY (ingredient_id);


ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_email_key UNIQUE (email);


ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (user_id);


ALTER TABLE ONLY public.pet_allergy
    ADD CONSTRAINT pet_allergy_pkey PRIMARY KEY (pet_id, ingredient_id);


ALTER TABLE ONLY public.pet
    ADD CONSTRAINT pet_pkey PRIMARY KEY (pet_id);


ALTER TABLE ONLY public.breed_disease
    ADD CONSTRAINT breed_disease_breed_id_fkey FOREIGN KEY (breed_id) REFERENCES public.breed(breed_id) ON DELETE CASCADE;


ALTER TABLE ONLY public.breed_disease
    ADD CONSTRAINT breed_disease_disease_id_fkey FOREIGN KEY (disease_id) REFERENCES public.disease(disease_id) ON DELETE CASCADE;


ALTER TABLE ONLY public.food_ingredient
    ADD CONSTRAINT food_ingredient_food_id_fkey FOREIGN KEY (food_id) REFERENCES public.food(food_id) ON DELETE CASCADE;

ALTER TABLE ONLY public.food_ingredient
    ADD CONSTRAINT food_ingredient_ingredient_id_fkey FOREIGN KEY (ingredient_id) REFERENCES public.ingredient(ingredient_id) ON DELETE CASCADE;


ALTER TABLE ONLY public.pet_allergy
    ADD CONSTRAINT pet_allergy_ingredient_id_fkey FOREIGN KEY (ingredient_id) REFERENCES public.ingredient(ingredient_id) ON DELETE CASCADE;


ALTER TABLE ONLY public.pet_allergy
    ADD CONSTRAINT pet_allergy_pet_id_fkey FOREIGN KEY (pet_id) REFERENCES public.pet(pet_id) ON DELETE CASCADE;


ALTER TABLE ONLY public.pet
    ADD CONSTRAINT pet_breed_id_fkey FOREIGN KEY (breed_id) REFERENCES public.breed(breed_id) ON DELETE SET NULL;


ALTER TABLE ONLY public.pet
    ADD CONSTRAINT pet_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.member(user_id) ON DELETE CASCADE;
