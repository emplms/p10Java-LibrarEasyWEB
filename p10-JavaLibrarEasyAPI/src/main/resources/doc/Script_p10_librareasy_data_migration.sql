--
-- PostgreSQL database dump
--

--
-- TOC entry 3214 (class 0 OID 83022)
-- Dependencies: 203
-- Data for Name: book_reservation_entities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book_reservation_entities (book_entity_book_id, reservation_entities_reservation_id) FROM stdin;
\.




--
-- TOC entry 3213 (class 0 OID 83007)
-- Dependencies: 202
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservation (reservation_id, notification_date, "position", book_id, user_id) FROM stdin;
184	\N	1	11	1
\.

