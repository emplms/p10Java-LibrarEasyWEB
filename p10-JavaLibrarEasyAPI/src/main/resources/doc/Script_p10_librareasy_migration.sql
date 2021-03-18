
CREATE TABLE public.book_reservation_entities (
    book_entity_book_id bigint NOT NULL,
    reservation_entities_reservation_id bigint NOT NULL
);


ALTER TABLE public.book_reservation_entities OWNER TO postgres;





CREATE TABLE public.reservation (
    reservation_id bigint NOT NULL,
    notification_date timestamp without time zone,
    "position" integer NOT NULL,
    book_id bigint,
    user_id bigint
);


ALTER TABLE public.reservation OWNER TO postgres;


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
-- TOC entry 3084 (class 2606 OID 83027)
-- Name: book_reservation_entities fko8qn5cpctwsr6s8m98dfatadj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_reservation_entities
    ADD CONSTRAINT fko8qn5cpctwsr6s8m98dfatadj FOREIGN KEY (reservation_entities_reservation_id) REFERENCES public.reservation(reservation_id);


-- Completed on 2021-03-18 09:35:54 CET

--
-- 
--

