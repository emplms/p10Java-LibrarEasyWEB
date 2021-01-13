--
-- PostgreSQL database dump
--

-- Dumped from database version 11.8
-- Dumped by pg_dump version 12.2

-- Started on 2020-09-09 14:45:59 CEST

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

--
-- TOC entry 3193 (class 0 OID 58238)
-- Dependencies: 198
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.author (author_id, author_first_name, author_last_name) FROM stdin;
1	Alexandre	Dumas
2	Jonathan	Coe
3	Jean	Tulard
6	Ken	Follet
75	Daniel	PENNAC
\.


--
-- TOC entry 3192 (class 0 OID 50207)
-- Dependencies: 197
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book (book_id, book_type, editor, book_title, author_id) FROM stdin;
1	Roman	Gallimard	Le comte de Monte Cristo	1
2	Roman	Folio	Bienvenue au club	2
3	Roman	Folio	Les 3 mousquetaires	1
11	Roman	Robert Laffont	Un monde sans fin	6
77	Roman	Gallimard	Chagrin d'école	75
\.


--
-- TOC entry 3195 (class 0 OID 66430)
-- Dependencies: 200
-- Data for Name: borrow; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.borrow (borrow_id, end_date, start_date, copy_id, user_id, is_extended, is_returned) FROM stdin;
1	2020-07-10	2020-06-12	1	1	f	t
2	2020-06-11	2020-06-25	2	3	f	t
3	2020-07-20	2020-06-22	3	2	f	t
4	2020-07-30	2020-06-10	4	1	f	t
5	2020-07-30	2020-07-01	10	2	f	t
7	2020-10-15	2020-08-18	11	1	t	f
9	2020-10-15	2020-08-18	8	1	t	f
8	2020-10-15	2020-08-18	3	1	t	t
83	2020-10-07	2020-09-07	79	1	f	f
6	2020-08-10	2020-07-11	11	1	f	f
11	2020-10-03	2020-09-03	2	1	f	f
\.


--
-- TOC entry 3194 (class 0 OID 58251)
-- Dependencies: 199
-- Data for Name: copy; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.copy (copy_id, book_id) FROM stdin;
1	1
2	1
3	2
4	2
5	1
6	2
7	2
8	1
9	2
10	3
11	3
16	11
59	11
60	11
79	77
\.


--
-- TOC entry 3196 (class 0 OID 66440)
-- Dependencies: 201
-- Data for Name: library_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.library_user (user_id, user_email, user_first_name, user_last_name, user_password, user_phone_number) FROM stdin;
2	ingrid@free.fr	Ingrid	Livio	$2a$10$HZfxR.PACjSqhMmLq8UwLus62uhM2YdNRCe88w1NbAukNo6WtOoZ2	0612345678
4	Sherlock@free.fr	Sherlock	Badiere	$2a$10$KI18ZQPxDvm.vNuSlmoNc.VCXMKIXWl8VhN33pxbTgcYpW9LBlyuq	0212345678
3	Eliot@free.fr	Eliot	Brissot	$2a$10$EJRbl4BMB6QEPIioPZ3tDO0UBeUlBBX0xxNFRMwAxvT7eV/L70sMu	0712345678
1	eplumas@free.fr	Emmanuel	Plumas	$2a$10$yeZ9A1KKuMCMbLN33GWa3emlqb3IdimWfRJNGwO1ScJDOBEf9EJlK	0512345678
9	lisa@free.fr	Lisa	Marie	$2a$10$LzW/3QfFWBlWydwoSdSYMuFgAMNyZVo29H/wwO03Zmfn5bDHKnFn6	0401020305
80	dupont@free.fr	Martin	Dupont	$2a$10$PXRyey/f0s9mTUIG1MKTDO5txYRiqPXRcJoc3z3KXZNoNXCN.pkxK	9999999999
\.


--
-- TOC entry 3202 (class 0 OID 0)
-- Dependencies: 196
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 86, true);


-- Completed on 2020-09-09 14:46:00 CEST

--
-- PostgreSQL database dump complete
--

