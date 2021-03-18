--
-- PostgreSQL database dump
--

-- Dumped from database version 11.11
-- Dumped by pg_dump version 12.2

-- Started on 2021-03-18 09:35:54 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- TOC entry 196 (class 1259 OID 82951)
-- Name: author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.author (
    author_id bigint NOT NULL,
    author_first_name character varying(255),
    author_last_name character varying(255)
);


ALTER TABLE public.author OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 82957)
-- Name: book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book (
    book_id bigint NOT NULL,
    book_type character varying(255),
    editor character varying(255),
    book_title character varying(255),
    author_id bigint
);


ALTER TABLE public.book OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 83022)
-- Name: book_reservation_entities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_reservation_entities (
    book_entity_book_id bigint NOT NULL,
    reservation_entities_reservation_id bigint NOT NULL
);


ALTER TABLE public.book_reservation_entities OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 82963)
-- Name: borrow; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.borrow (
    borrow_id bigint NOT NULL,
    end_date date,
    start_date date,
    copy_id bigint,
    user_id bigint,
    is_extended boolean,
    is_returned boolean
);


ALTER TABLE public.borrow OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 82966)
-- Name: copy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.copy (
    copy_id bigint NOT NULL,
    book_id bigint
);


ALTER TABLE public.copy OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 82969)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 82971)
-- Name: library_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.library_user (
    user_id bigint NOT NULL,
    user_email character varying(255),
    user_first_name character varying(255),
    user_last_name character varying(255),
    user_password character varying(255),
    user_phone_number character varying(255)
);


ALTER TABLE public.library_user OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 83007)
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservation (
    reservation_id bigint NOT NULL,
    notification_date timestamp without time zone,
    "position" integer NOT NULL,
    book_id bigint,
    user_id bigint
);


ALTER TABLE public.reservation OWNER TO postgres;

--
-- TOC entry 3065 (class 2606 OID 82978)
-- Name: author author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (author_id);


--
-- TOC entry 3067 (class 2606 OID 82980)
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (book_id);


--
-- TOC entry 3069 (class 2606 OID 82982)
-- Name: borrow borrow_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.borrow
    ADD CONSTRAINT borrow_pkey PRIMARY KEY (borrow_id);


--
-- TOC entry 3071 (class 2606 OID 82984)
-- Name: copy copy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.copy
    ADD CONSTRAINT copy_pkey PRIMARY KEY (copy_id);


--
-- TOC entry 3073 (class 2606 OID 82986)
-- Name: library_user library_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.library_user
    ADD CONSTRAINT library_user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3075 (class 2606 OID 83011)
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);


--
-- TOC entry 3077 (class 2606 OID 83026)
-- Name: book_reservation_entities uk_e87f1988svfy9mw4p075hax0v; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_reservation_entities
    ADD CONSTRAINT uk_e87f1988svfy9mw4p075hax0v UNIQUE (reservation_entities_reservation_id);


--
-- TOC entry 3083 (class 2606 OID 83017)
-- Name: reservation fk9rp3xsj4p96vx5p73ucfy3bey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fk9rp3xsj4p96vx5p73ucfy3bey FOREIGN KEY (user_id) REFERENCES public.library_user(user_id);


--
-- TOC entry 3079 (class 2606 OID 82987)
-- Name: borrow fkal7rjl0jras1uk9j9t118s3a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.borrow
    ADD CONSTRAINT fkal7rjl0jras1uk9j9t118s3a FOREIGN KEY (user_id) REFERENCES public.library_user(user_id);


--
-- TOC entry 3080 (class 2606 OID 82992)
-- Name: borrow fkdxvm90wmembudkvcoguvxa9yp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.borrow
    ADD CONSTRAINT fkdxvm90wmembudkvcoguvxa9yp FOREIGN KEY (copy_id) REFERENCES public.copy(copy_id);


--
-- TOC entry 3085 (class 2606 OID 83032)
-- Name: book_reservation_entities fkedjomikl0psj02wcabyc9il9v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_reservation_entities
    ADD CONSTRAINT fkedjomikl0psj02wcabyc9il9v FOREIGN KEY (book_entity_book_id) REFERENCES public.book(book_id);


--
-- TOC entry 3082 (class 2606 OID 83012)
-- Name: reservation fkirxtcw4s6lhwi6l9ocrk6bjfy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fkirxtcw4s6lhwi6l9ocrk6bjfy FOREIGN KEY (book_id) REFERENCES public.book(book_id);


--
-- TOC entry 3078 (class 2606 OID 82997)
-- Name: book fkklnrv3weler2ftkweewlky958; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkklnrv3weler2ftkweewlky958 FOREIGN KEY (author_id) REFERENCES public.author(author_id);


--
-- TOC entry 3084 (class 2606 OID 83027)
-- Name: book_reservation_entities fko8qn5cpctwsr6s8m98dfatadj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_reservation_entities
    ADD CONSTRAINT fko8qn5cpctwsr6s8m98dfatadj FOREIGN KEY (reservation_entities_reservation_id) REFERENCES public.reservation(reservation_id);


--
-- TOC entry 3081 (class 2606 OID 83002)
-- Name: copy fkof5k7k6c41i06j6fj3slgsmam; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.copy
    ADD CONSTRAINT fkof5k7k6c41i06j6fj3slgsmam FOREIGN KEY (book_id) REFERENCES public.book(book_id);


-- Completed on 2021-03-18 09:35:54 CET

--
-- PostgreSQL database dump complete
--

