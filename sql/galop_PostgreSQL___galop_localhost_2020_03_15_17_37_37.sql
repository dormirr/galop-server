--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

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

DROP DATABASE IF EXISTS galop;
--
-- Name: galop; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE galop WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Chinese (Simplified)_China.936' LC_CTYPE = 'Chinese (Simplified)_China.936';


ALTER DATABASE galop OWNER TO postgres;

\connect galop

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
-- Name: DATABASE galop; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE galop IS '学科竞赛训练管理系统数据库';


--
-- Name: galop; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA galop;


ALTER SCHEMA galop OWNER TO postgres;

--
-- Name: SCHEMA galop; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA galop IS '学科竞赛训练管理系统数据库';


--
-- Name: modification_time(); Type: FUNCTION; Schema: galop; Owner: postgres
--

CREATE FUNCTION galop.modification_time() RETURNS trigger
    LANGUAGE plpgsql
    AS $$begin 
    new.update_time = current_timestamp;
    return new;
end$$;


ALTER FUNCTION galop.modification_time() OWNER TO postgres;

--
-- Name: FUNCTION modification_time(); Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON FUNCTION galop.modification_time() IS '修改时间';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: role; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.role (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    role_name character varying(50) NOT NULL
);


ALTER TABLE galop.role OWNER TO postgres;

--
-- Name: TABLE role; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.role IS '角色表';


--
-- Name: COLUMN role.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.role.id IS '主键';


--
-- Name: COLUMN role.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.role.create_time IS '创建时间';


--
-- Name: COLUMN role.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.role.update_time IS '修改时间';


--
-- Name: COLUMN role.role_name; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.role.role_name IS '角色名';


--
-- Name: role_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.role_id_seq OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.role_id_seq OWNED BY galop.role.id;


--
-- Name: user_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.user_id_seq OWNER TO postgres;

--
-- Name: user; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop."user" (
    id bigint DEFAULT nextval('galop.user_id_seq'::regclass) NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    user_number character varying(12) NOT NULL,
    user_name character varying(50) NOT NULL,
    user_password character varying(172) DEFAULT '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om'::character varying NOT NULL,
    user_email character varying(50),
    user_portrait character varying(50) NOT NULL,
    user_fighting_capacity integer DEFAULT 0 NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE galop."user" OWNER TO postgres;

--
-- Name: TABLE "user"; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop."user" IS '用户表';


--
-- Name: COLUMN "user".id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".id IS '主键';


--
-- Name: COLUMN "user".create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".create_time IS '创建时间';


--
-- Name: COLUMN "user".update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".update_time IS '修改时间';


--
-- Name: COLUMN "user".user_number; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".user_number IS '学号';


--
-- Name: COLUMN "user".user_name; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".user_name IS '用户名';


--
-- Name: COLUMN "user".user_password; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".user_password IS '密码';


--
-- Name: COLUMN "user".user_email; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".user_email IS '邮箱';


--
-- Name: COLUMN "user".user_portrait; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".user_portrait IS '头像';


--
-- Name: COLUMN "user".user_fighting_capacity; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".user_fighting_capacity IS '战斗力';


--
-- Name: COLUMN "user".role_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".role_id IS '角色id';


--
-- Name: user_role_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.user_role_id_seq OWNER TO postgres;

--
-- Name: user_role_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.user_role_id_seq OWNED BY galop."user".role_id;


--
-- Name: role id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.role ALTER COLUMN id SET DEFAULT nextval('galop.role_id_seq'::regclass);


--
-- Name: user role_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."user" ALTER COLUMN role_id SET DEFAULT nextval('galop.user_role_id_seq'::regclass);


--
-- Data for Name: role; Type: TABLE DATA; Schema: galop; Owner: postgres
--

COPY galop.role (id, create_time, update_time, role_name) FROM stdin;
1	2020-02-29 07:54:39.726039	2020-03-02 12:55:08.109629	老师
2	2020-03-12 16:31:15.609138	2020-03-12 16:31:15.609138	学生
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: galop; Owner: postgres
--

COPY galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, user_fighting_capacity, role_id) FROM stdin;
1	2020-03-12 16:32:23.802433	2020-03-15 16:12:55.721325	admin	老师	$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om	\N	1	0	1
2	2020-03-12 16:31:28.541473	2020-03-15 16:12:55.783225	201620205052	张天赐	$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om	\N	1	0	2
\.


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.role_id_seq', 2, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.user_id_seq', 3, true);


--
-- Name: user_role_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.user_role_id_seq', 1, false);


--
-- Name: role role_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.role
    ADD CONSTRAINT role_pk PRIMARY KEY (id);


--
-- Name: user user_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- Name: user_role_id_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX user_role_id_uindex ON galop."user" USING btree (role_id);


--
-- Name: user_user_number_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX user_user_number_uindex ON galop."user" USING btree (user_number);


--
-- Name: role modification_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER modification_time BEFORE UPDATE ON galop.role FOR EACH ROW EXECUTE FUNCTION galop.modification_time();


--
-- Name: TRIGGER modification_time ON role; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER modification_time ON galop.role IS '修改时间';


--
-- Name: user modification_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER modification_time BEFORE UPDATE ON galop."user" FOR EACH ROW EXECUTE FUNCTION galop.modification_time();


--
-- Name: TRIGGER modification_time ON "user"; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER modification_time ON galop."user" IS '修改时间';


--
-- Name: user user_role_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."user"
    ADD CONSTRAINT user_role_id_fk FOREIGN KEY (role_id) REFERENCES galop.role(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

