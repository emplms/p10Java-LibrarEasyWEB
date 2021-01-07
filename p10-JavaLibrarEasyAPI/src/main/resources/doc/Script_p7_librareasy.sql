--
-- PostgreSQL database dump
--

-- Dumped from database version 11.8
-- Dumped by pg_dump version 12.2

-- Started on 2020-09-09 14:44:11 CEST

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
-- TOC entry 198 (class 1259 OID 58238)
-- Name: author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.author (
    author_id bigint NOT NULL,
    author_first_name character varying(255),
    author_last_name character varying(255)
);


ALTER TABLE public.author OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 50207)
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
-- TOC entry 200 (class 1259 OID 66430)
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
-- TOC entry 199 (class 1259 OID 58251)
-- Name: copy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.copy (
    copy_id bigint NOT NULL,
    book_id bigint
);


ALTER TABLE public.copy OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 50205)
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
-- TOC entry 201 (class 1259 OID 66440)
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
-- TOC entry 3059 (class 2606 OID 58245)
-- Name: author author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (author_id);


--
-- TOC entry 3057 (class 2606 OID 50214)
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (book_id);


--
-- TOC entry 3063 (class 2606 OID 66454)
-- Name: borrow borrow_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.borrow
    ADD CONSTRAINT borrow_pkey PRIMARY KEY (borrow_id);


--
-- TOC entry 3061 (class 2606 OID 58255)
-- Name: copy copy_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.copy
    ADD CONSTRAINT copy_pkey PRIMARY KEY (copy_id);


--
-- TOC entry 3065 (class 2606 OID 66447)
-- Name: library_user library_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.library_user
    ADD CONSTRAINT library_user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3069 (class 2606 OID 66448)
-- Name: borrow fkal7rjl0jras1uk9j9t118s3a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.borrow
    ADD CONSTRAINT fkal7rjl0jras1uk9j9t118s3a FOREIGN KEY (user_id) REFERENCES public.library_user(user_id);


--
-- TOC entry 3068 (class 2606 OID 66435)
-- Name: borrow fkdxvm90wmembudkvcoguvxa9yp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.borrow
    ADD CONSTRAINT fkdxvm90wmembudkvcoguvxa9yp FOREIGN KEY (copy_id) REFERENCES public.copy(copy_id);


--
-- TOC entry 3066 (class 2606 OID 58246)
-- Name: book fkklnrv3weler2ftkweewlky958; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fkklnrv3weler2ftkweewlky958 FOREIGN KEY (author_id) REFERENCES public.author(author_id);


--
-- TOC entry 3067 (class 2606 OID 58256)
-- Name: copy fkof5k7k6c41i06j6fj3slgsmam; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.copy
    ADD CONSTRAINT fkof5k7k6c41i06j6fj3slgsmam FOREIGN KEY (book_id) REFERENCES public.book(book_id);


-- Completed on 2020-09-09 14:44:13 CEST

--
-- PostgreSQL database dump complete
--

