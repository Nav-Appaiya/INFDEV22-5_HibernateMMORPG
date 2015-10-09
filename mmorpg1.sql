--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
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
-- Name: owns; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE owns (
    id bigint NOT NULL,
    name bigint,
    user_name bigint
);


ALTER TABLE owns OWNER TO postgres;

--
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
-- Name: stores; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE stores (
    id bigint NOT NULL,
    address character varying(255),
    user_name character varying(255)
);


ALTER TABLE stores OWNER TO postgres;

--
-- Data for Name: character; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "character" (id, charclass, level, name, race, player_id) FROM stdin;
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 9091, true);


--
-- Data for Name: owns; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY owns (id, name, user_name) FROM stdin;
\.


--
-- Data for Name: player; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY player (id, balance, banned, characterslots, firstname, iban, lastname, lastpayment, monthspayed, password, username) FROM stdin;
1	1000	false	5	Tim	234234fsdfsd	Mishutin	\N	\N	tim	tim
2	1000	false	5	Nav	2353453sfdfsd	Appaiya	\N	\N	nav	nav
\.


--
-- Data for Name: server; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY server (id, adress, connectedusers, location, maxusers, name, player_id) FROM stdin;
1	123.123.123.23	\N	Germany	120	Pulic game server	1
2	123.123.123.24	\N	Germany	2000	Huge game server	2
\.


--
-- Data for Name: stores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY stores (id, address, user_name) FROM stdin;
\.


--
-- Name: character_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY "character"
    ADD CONSTRAINT character_pkey PRIMARY KEY (id);


--
-- Name: owns_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY owns
    ADD CONSTRAINT owns_pkey PRIMARY KEY (id);


--
-- Name: player_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY player
    ADD CONSTRAINT player_pkey PRIMARY KEY (id);


--
-- Name: server_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY server
    ADD CONSTRAINT server_pkey PRIMARY KEY (id);


--
-- Name: uk_biow3y9in5mjr2g0bffhnnco6; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY player
    ADD CONSTRAINT uk_biow3y9in5mjr2g0bffhnnco6 UNIQUE (username);


--
-- Name: unq_addr; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY server
    ADD CONSTRAINT unq_addr UNIQUE (adress);


--
-- Name: name_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace:
--

CREATE INDEX name_idx ON "character" USING btree (name);


--
-- Name: password_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace:
--

CREATE INDEX password_idx ON player USING btree (password);


--
-- Name: username_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace:
--

CREATE INDEX username_idx ON player USING btree (username);


--
-- Name: fk_4kkxp3xqttgtqaut9uylr09os; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY server
    ADD CONSTRAINT fk_4kkxp3xqttgtqaut9uylr09os FOREIGN KEY (player_id) REFERENCES player(id);


--
-- Name: fk_50fhl1tf2iip0bl0x7skxa0fn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "character"
    ADD CONSTRAINT fk_50fhl1tf2iip0bl0x7skxa0fn FOREIGN KEY (player_id) REFERENCES player(id);


--
-- Name: fk_6qp37v3872be7s9fbju8w654y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY owns
    ADD CONSTRAINT fk_6qp37v3872be7s9fbju8w654y FOREIGN KEY (user_name) REFERENCES player(id);


--
-- Name: fk_s2yhhyh1v6s3xw2oaojlpnu7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY owns
    ADD CONSTRAINT fk_s2yhhyh1v6s3xw2oaojlpnu7 FOREIGN KEY (name) REFERENCES "character"(id);


--
-- Name: player_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY stores
    ADD CONSTRAINT player_fk FOREIGN KEY (user_name) REFERENCES player(username);


--
-- Name: server_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY stores
    ADD CONSTRAINT server_fk FOREIGN KEY (address) REFERENCES server(adress);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--