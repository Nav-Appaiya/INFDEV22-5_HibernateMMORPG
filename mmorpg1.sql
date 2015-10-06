--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.4
-- Dumped by pg_dump version 9.4.4
-- Started on 2015-10-06 23:03:42 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE mmorpg;
--
-- TOC entry 2294 (class 1262 OID 16393)
-- Name: mmorpg; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE mmorpg WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';


ALTER DATABASE mmorpg OWNER TO postgres;

\connect mmorpg

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2295 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 177 (class 3079 OID 12123)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2297 (class 0 OID 0)
-- Dependencies: 177
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 18966)
-- Name: character; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "character" (
    id bigint NOT NULL,
    charclass character varying(255),
    level integer NOT NULL,
    name character varying(255),
    race character varying(255),
    player_id bigint
);


ALTER TABLE "character" OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 19017)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 18974)
-- Name: owns; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE owns (
    id bigint NOT NULL,
    name bigint,
    user_name bigint
);


ALTER TABLE owns OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 18979)
-- Name: player; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE player (
    id bigint NOT NULL,
    balance character varying(255),
    banned character varying(255),
    characterslots character varying(255),
    firstname character varying(255),
    iban character varying(255),
    lastname character varying(255),
    lastpayment character varying(255),
    monthspayed character varying(255),
    password character varying(255),
    username character varying(255)
);


ALTER TABLE player OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 18987)
-- Name: server; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE server (
    id bigint NOT NULL,
    adress character varying(255),
    connectedusers character varying(255),
    location character varying(255),
    maxusers character varying(255),
    name character varying(255),
    player_id bigint
);


ALTER TABLE server OWNER TO postgres;

--
-- TOC entry 2285 (class 0 OID 18966)
-- Dependencies: 172
-- Data for Name: character; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2298 (class 0 OID 0)
-- Dependencies: 176
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 3, true);


--
-- TOC entry 2286 (class 0 OID 18974)
-- Dependencies: 173
-- Data for Name: owns; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2287 (class 0 OID 18979)
-- Dependencies: 174
-- Data for Name: player; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO player (id, balance, banned, characterslots, firstname, iban, lastname, lastpayment, monthspayed, password, username) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nav', 'nav');
INSERT INTO player (id, balance, banned, characterslots, firstname, iban, lastname, lastpayment, monthspayed, password, username) VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '');


--
-- TOC entry 2288 (class 0 OID 18987)
-- Dependencies: 175
-- Data for Name: server; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2163 (class 2606 OID 18973)
-- Name: character_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "character"
    ADD CONSTRAINT character_pkey PRIMARY KEY (id);


--
-- TOC entry 2165 (class 2606 OID 18978)
-- Name: owns_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY owns
    ADD CONSTRAINT owns_pkey PRIMARY KEY (id);


--
-- TOC entry 2167 (class 2606 OID 18986)
-- Name: player_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY player
    ADD CONSTRAINT player_pkey PRIMARY KEY (id);


--
-- TOC entry 2171 (class 2606 OID 18994)
-- Name: server_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY server
    ADD CONSTRAINT server_pkey PRIMARY KEY (id);


--
-- TOC entry 2169 (class 2606 OID 18996)
-- Name: uk_biow3y9in5mjr2g0bffhnnco6; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY player
    ADD CONSTRAINT uk_biow3y9in5mjr2g0bffhnnco6 UNIQUE (username);


--
-- TOC entry 2175 (class 2606 OID 19012)
-- Name: fk_4kkxp3xqttgtqaut9uylr09os; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY server
    ADD CONSTRAINT fk_4kkxp3xqttgtqaut9uylr09os FOREIGN KEY (player_id) REFERENCES player(id);


--
-- TOC entry 2172 (class 2606 OID 18997)
-- Name: fk_50fhl1tf2iip0bl0x7skxa0fn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "character"
    ADD CONSTRAINT fk_50fhl1tf2iip0bl0x7skxa0fn FOREIGN KEY (player_id) REFERENCES player(id);


--
-- TOC entry 2174 (class 2606 OID 19007)
-- Name: fk_6qp37v3872be7s9fbju8w654y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY owns
    ADD CONSTRAINT fk_6qp37v3872be7s9fbju8w654y FOREIGN KEY (user_name) REFERENCES player(id);


--
-- TOC entry 2173 (class 2606 OID 19002)
-- Name: fk_s2yhhyh1v6s3xw2oaojlpnu7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY owns
    ADD CONSTRAINT fk_s2yhhyh1v6s3xw2oaojlpnu7 FOREIGN KEY (name) REFERENCES "character"(id);


--
-- TOC entry 2296 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-10-06 23:03:42 CEST

--
-- PostgreSQL database dump complete
--

