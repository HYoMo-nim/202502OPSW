--
-- PostgreSQL database dump
--

\restrict ucB4KEM2rygPMqc3zGgnTY6G0hkPeE5RpQX4BfTZwgK9MOG0Mn2zEf3cjNfCUJu

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

-- Started on 2025-11-02 02:20:34

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 16537)
-- Name: breed; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.breed (
    breed_id bigint NOT NULL,
    breed_name character varying(100) NOT NULL,
    species character varying(20) NOT NULL
);


ALTER TABLE public.breed OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16536)
-- Name: breed_breed_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.breed_breed_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.breed_breed_id_seq OWNER TO postgres;

--
-- TOC entry 5099 (class 0 OID 0)
-- Dependencies: 221
-- Name: breed_breed_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.breed_breed_id_seq OWNED BY public.breed.breed_id;


--
-- TOC entry 231 (class 1259 OID 16600)
-- Name: breed_disease; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.breed_disease (
    breed_id bigint NOT NULL,
    disease_id bigint NOT NULL
);


ALTER TABLE public.breed_disease OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16547)
-- Name: disease; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.disease (
    disease_id bigint NOT NULL,
    disease_name character varying(255) NOT NULL,
    description text,
    is_genetic boolean DEFAULT false
);


ALTER TABLE public.disease OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16546)
-- Name: disease_disease_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.disease_disease_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.disease_disease_id_seq OWNER TO postgres;

--
-- TOC entry 5100 (class 0 OID 0)
-- Dependencies: 223
-- Name: disease_disease_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.disease_disease_id_seq OWNED BY public.disease.disease_id;


--
-- TOC entry 228 (class 1259 OID 16571)
-- Name: food; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.food (
    food_id bigint NOT NULL,
    food_name character varying(255) NOT NULL,
    manufacturer character varying(100),
    food_type character varying(20) NOT NULL,
    species_target character varying(20)
);


ALTER TABLE public.food OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16570)
-- Name: food_food_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.food_food_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.food_food_id_seq OWNER TO postgres;

--
-- TOC entry 5101 (class 0 OID 0)
-- Dependencies: 227
-- Name: food_food_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.food_food_id_seq OWNED BY public.food.food_id;


--
-- TOC entry 233 (class 1259 OID 16634)
-- Name: food_ingredient; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.food_ingredient (
    food_id bigint NOT NULL,
    ingredient_id bigint NOT NULL,
    ingredient_order integer
);


ALTER TABLE public.food_ingredient OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16559)
-- Name: ingredient; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ingredient (
    ingredient_id bigint NOT NULL,
    ingredient_name character varying(100) NOT NULL,
    is_known_allergen boolean DEFAULT false
);


ALTER TABLE public.ingredient OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16558)
-- Name: ingredient_ingredient_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ingredient_ingredient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ingredient_ingredient_id_seq OWNER TO postgres;

--
-- TOC entry 5102 (class 0 OID 0)
-- Dependencies: 225
-- Name: ingredient_ingredient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ingredient_ingredient_id_seq OWNED BY public.ingredient.ingredient_id;


--
-- TOC entry 220 (class 1259 OID 16522)
-- Name: member; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.member (
    user_id bigint NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    nickname character varying(50),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.member OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16521)
-- Name: member_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.member_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.member_user_id_seq OWNER TO postgres;

--
-- TOC entry 5103 (class 0 OID 0)
-- Dependencies: 219
-- Name: member_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.member_user_id_seq OWNED BY public.member.user_id;


--
-- TOC entry 230 (class 1259 OID 16581)
-- Name: pet; Type: TABLE; Schema: public; Owner: postgres
--

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


ALTER TABLE public.pet OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 16617)
-- Name: pet_allergy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pet_allergy (
    pet_id bigint NOT NULL,
    ingredient_id bigint NOT NULL,
    reaction_type character varying(100)
);


ALTER TABLE public.pet_allergy OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16580)
-- Name: pet_pet_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pet_pet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pet_pet_id_seq OWNER TO postgres;

--
-- TOC entry 5104 (class 0 OID 0)
-- Dependencies: 229
-- Name: pet_pet_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pet_pet_id_seq OWNED BY public.pet.pet_id;


--
-- TOC entry 4895 (class 2604 OID 16540)
-- Name: breed breed_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.breed ALTER COLUMN breed_id SET DEFAULT nextval('public.breed_breed_id_seq'::regclass);


--
-- TOC entry 4896 (class 2604 OID 16550)
-- Name: disease disease_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disease ALTER COLUMN disease_id SET DEFAULT nextval('public.disease_disease_id_seq'::regclass);


--
-- TOC entry 4900 (class 2604 OID 16574)
-- Name: food food_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food ALTER COLUMN food_id SET DEFAULT nextval('public.food_food_id_seq'::regclass);


--
-- TOC entry 4898 (class 2604 OID 16562)
-- Name: ingredient ingredient_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient ALTER COLUMN ingredient_id SET DEFAULT nextval('public.ingredient_ingredient_id_seq'::regclass);


--
-- TOC entry 4893 (class 2604 OID 16525)
-- Name: member user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member ALTER COLUMN user_id SET DEFAULT nextval('public.member_user_id_seq'::regclass);


--
-- TOC entry 4901 (class 2604 OID 16584)
-- Name: pet pet_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet ALTER COLUMN pet_id SET DEFAULT nextval('public.pet_pet_id_seq'::regclass);


--
-- TOC entry 5082 (class 0 OID 16537)
-- Dependencies: 222
-- Data for Name: breed; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.breed (breed_id, breed_name, species) FROM stdin;
\.


--
-- TOC entry 5091 (class 0 OID 16600)
-- Dependencies: 231
-- Data for Name: breed_disease; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.breed_disease (breed_id, disease_id) FROM stdin;
\.


--
-- TOC entry 5084 (class 0 OID 16547)
-- Dependencies: 224
-- Data for Name: disease; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.disease (disease_id, disease_name, description, is_genetic) FROM stdin;
\.


--
-- TOC entry 5088 (class 0 OID 16571)
-- Dependencies: 228
-- Data for Name: food; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.food (food_id, food_name, manufacturer, food_type, species_target) FROM stdin;
\.


--
-- TOC entry 5093 (class 0 OID 16634)
-- Dependencies: 233
-- Data for Name: food_ingredient; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.food_ingredient (food_id, ingredient_id, ingredient_order) FROM stdin;
\.


--
-- TOC entry 5086 (class 0 OID 16559)
-- Dependencies: 226
-- Data for Name: ingredient; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ingredient (ingredient_id, ingredient_name, is_known_allergen) FROM stdin;
\.


--
-- TOC entry 5080 (class 0 OID 16522)
-- Dependencies: 220
-- Data for Name: member; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.member (user_id, email, password, nickname, created_at) FROM stdin;
\.


--
-- TOC entry 5090 (class 0 OID 16581)
-- Dependencies: 230
-- Data for Name: pet; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pet (pet_id, user_id, breed_id, name, birth_date, gender, weight_kg, profile_img_url) FROM stdin;
\.


--
-- TOC entry 5092 (class 0 OID 16617)
-- Dependencies: 232
-- Data for Name: pet_allergy; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pet_allergy (pet_id, ingredient_id, reaction_type) FROM stdin;
\.


--
-- TOC entry 5105 (class 0 OID 0)
-- Dependencies: 221
-- Name: breed_breed_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.breed_breed_id_seq', 1, false);


--
-- TOC entry 5106 (class 0 OID 0)
-- Dependencies: 223
-- Name: disease_disease_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.disease_disease_id_seq', 1, false);


--
-- TOC entry 5107 (class 0 OID 0)
-- Dependencies: 227
-- Name: food_food_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.food_food_id_seq', 1, false);


--
-- TOC entry 5108 (class 0 OID 0)
-- Dependencies: 225
-- Name: ingredient_ingredient_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ingredient_ingredient_id_seq', 1, false);


--
-- TOC entry 5109 (class 0 OID 0)
-- Dependencies: 219
-- Name: member_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.member_user_id_seq', 1, false);


--
-- TOC entry 5110 (class 0 OID 0)
-- Dependencies: 229
-- Name: pet_pet_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pet_pet_id_seq', 1, false);


--
-- TOC entry 4919 (class 2606 OID 16606)
-- Name: breed_disease breed_disease_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.breed_disease
    ADD CONSTRAINT breed_disease_pkey PRIMARY KEY (breed_id, disease_id);


--
-- TOC entry 4907 (class 2606 OID 16545)
-- Name: breed breed_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.breed
    ADD CONSTRAINT breed_pkey PRIMARY KEY (breed_id);


--
-- TOC entry 4909 (class 2606 OID 16557)
-- Name: disease disease_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.disease
    ADD CONSTRAINT disease_pkey PRIMARY KEY (disease_id);


--
-- TOC entry 4923 (class 2606 OID 16640)
-- Name: food_ingredient food_ingredient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food_ingredient
    ADD CONSTRAINT food_ingredient_pkey PRIMARY KEY (food_id, ingredient_id);


--
-- TOC entry 4915 (class 2606 OID 16579)
-- Name: food food_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food
    ADD CONSTRAINT food_pkey PRIMARY KEY (food_id);


--
-- TOC entry 4911 (class 2606 OID 16569)
-- Name: ingredient ingredient_ingredient_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_ingredient_name_key UNIQUE (ingredient_name);


--
-- TOC entry 4913 (class 2606 OID 16567)
-- Name: ingredient ingredient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_pkey PRIMARY KEY (ingredient_id);


--
-- TOC entry 4903 (class 2606 OID 16535)
-- Name: member member_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_email_key UNIQUE (email);


--
-- TOC entry 4905 (class 2606 OID 16533)
-- Name: member member_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4921 (class 2606 OID 16623)
-- Name: pet_allergy pet_allergy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_allergy
    ADD CONSTRAINT pet_allergy_pkey PRIMARY KEY (pet_id, ingredient_id);


--
-- TOC entry 4917 (class 2606 OID 16589)
-- Name: pet pet_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet
    ADD CONSTRAINT pet_pkey PRIMARY KEY (pet_id);


--
-- TOC entry 4926 (class 2606 OID 16607)
-- Name: breed_disease breed_disease_breed_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.breed_disease
    ADD CONSTRAINT breed_disease_breed_id_fkey FOREIGN KEY (breed_id) REFERENCES public.breed(breed_id) ON DELETE CASCADE;


--
-- TOC entry 4927 (class 2606 OID 16612)
-- Name: breed_disease breed_disease_disease_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.breed_disease
    ADD CONSTRAINT breed_disease_disease_id_fkey FOREIGN KEY (disease_id) REFERENCES public.disease(disease_id) ON DELETE CASCADE;


--
-- TOC entry 4930 (class 2606 OID 16641)
-- Name: food_ingredient food_ingredient_food_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food_ingredient
    ADD CONSTRAINT food_ingredient_food_id_fkey FOREIGN KEY (food_id) REFERENCES public.food(food_id) ON DELETE CASCADE;


--
-- TOC entry 4931 (class 2606 OID 16646)
-- Name: food_ingredient food_ingredient_ingredient_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.food_ingredient
    ADD CONSTRAINT food_ingredient_ingredient_id_fkey FOREIGN KEY (ingredient_id) REFERENCES public.ingredient(ingredient_id) ON DELETE CASCADE;


--
-- TOC entry 4928 (class 2606 OID 16629)
-- Name: pet_allergy pet_allergy_ingredient_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_allergy
    ADD CONSTRAINT pet_allergy_ingredient_id_fkey FOREIGN KEY (ingredient_id) REFERENCES public.ingredient(ingredient_id) ON DELETE CASCADE;


--
-- TOC entry 4929 (class 2606 OID 16624)
-- Name: pet_allergy pet_allergy_pet_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet_allergy
    ADD CONSTRAINT pet_allergy_pet_id_fkey FOREIGN KEY (pet_id) REFERENCES public.pet(pet_id) ON DELETE CASCADE;


--
-- TOC entry 4924 (class 2606 OID 16595)
-- Name: pet pet_breed_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet
    ADD CONSTRAINT pet_breed_id_fkey FOREIGN KEY (breed_id) REFERENCES public.breed(breed_id) ON DELETE SET NULL;


--
-- TOC entry 4925 (class 2606 OID 16590)
-- Name: pet pet_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pet
    ADD CONSTRAINT pet_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.member(user_id) ON DELETE CASCADE;


-- Completed on 2025-11-02 02:20:35

--
-- PostgreSQL database dump complete
--

\unrestrict ucB4KEM2rygPMqc3zGgnTY6G0hkPeE5RpQX4BfTZwgK9MOG0Mn2zEf3cjNfCUJu

