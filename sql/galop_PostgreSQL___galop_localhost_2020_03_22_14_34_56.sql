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
-- Name: announcement; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.announcement (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    announcement_name text NOT NULL,
    announcement_content text NOT NULL
);


ALTER TABLE galop.announcement OWNER TO postgres;

--
-- Name: TABLE announcement; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.announcement IS '公告表';


--
-- Name: COLUMN announcement.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.announcement.id IS '主键';


--
-- Name: COLUMN announcement.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.announcement.create_time IS '创建时间';


--
-- Name: COLUMN announcement.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.announcement.update_time IS '修改时间';


--
-- Name: COLUMN announcement.announcement_name; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.announcement.announcement_name IS '公告标题';


--
-- Name: COLUMN announcement.announcement_content; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.announcement.announcement_content IS '公告内容';


--
-- Name: announcement_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.announcement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.announcement_id_seq OWNER TO postgres;

--
-- Name: announcement_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.announcement_id_seq OWNED BY galop.announcement.id;


--
-- Name: fighting_capacity; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.fighting_capacity (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    match_information_id bigint NOT NULL,
    increased_combat_effectiveness integer NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE galop.fighting_capacity OWNER TO postgres;

--
-- Name: TABLE fighting_capacity; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.fighting_capacity IS '战斗力变动表';


--
-- Name: COLUMN fighting_capacity.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.fighting_capacity.id IS '主键';


--
-- Name: COLUMN fighting_capacity.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.fighting_capacity.create_time IS '创建时间';


--
-- Name: COLUMN fighting_capacity.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.fighting_capacity.update_time IS '更新时间';


--
-- Name: COLUMN fighting_capacity.match_information_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.fighting_capacity.match_information_id IS '比赛信息id';


--
-- Name: COLUMN fighting_capacity.increased_combat_effectiveness; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.fighting_capacity.increased_combat_effectiveness IS '提升的战斗力';


--
-- Name: COLUMN fighting_capacity.user_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.fighting_capacity.user_id IS '用户id';


--
-- Name: fighting_capacity_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.fighting_capacity_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.fighting_capacity_id_seq OWNER TO postgres;

--
-- Name: fighting_capacity_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.fighting_capacity_id_seq OWNED BY galop.fighting_capacity.id;


--
-- Name: fighting_capacity_match_information_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.fighting_capacity_match_information_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.fighting_capacity_match_information_id_seq OWNER TO postgres;

--
-- Name: fighting_capacity_match_information_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.fighting_capacity_match_information_id_seq OWNED BY galop.fighting_capacity.match_information_id;


--
-- Name: fighting_capacity_user_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.fighting_capacity_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.fighting_capacity_user_id_seq OWNER TO postgres;

--
-- Name: fighting_capacity_user_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.fighting_capacity_user_id_seq OWNED BY galop.fighting_capacity.user_id;


--
-- Name: match_information; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.match_information (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    match_name character varying(50) NOT NULL,
    team_size integer DEFAULT 1 NOT NULL,
    first_place_combat_effectiveness integer NOT NULL,
    decrement_parameter integer NOT NULL
);


ALTER TABLE galop.match_information OWNER TO postgres;

--
-- Name: TABLE match_information; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.match_information IS '比赛信息表';


--
-- Name: COLUMN match_information.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_information.id IS '主键';


--
-- Name: COLUMN match_information.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_information.create_time IS '创建时间';


--
-- Name: COLUMN match_information.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_information.update_time IS '更新时间';


--
-- Name: COLUMN match_information.match_name; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_information.match_name IS '比赛名';


--
-- Name: COLUMN match_information.team_size; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_information.team_size IS '团队规模';


--
-- Name: COLUMN match_information.first_place_combat_effectiveness; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_information.first_place_combat_effectiveness IS '本场比赛第一名的战斗力';


--
-- Name: COLUMN match_information.decrement_parameter; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_information.decrement_parameter IS '递减参数';


--
-- Name: match_information_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.match_information_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.match_information_id_seq OWNER TO postgres;

--
-- Name: match_information_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.match_information_id_seq OWNED BY galop.match_information.id;


--
-- Name: match_result; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.match_result (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    match_information_id bigint NOT NULL,
    team_id bigint NOT NULL,
    ranking integer NOT NULL,
    increased_combat_effectiveness integer NOT NULL
);


ALTER TABLE galop.match_result OWNER TO postgres;

--
-- Name: TABLE match_result; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.match_result IS '比赛结果表';


--
-- Name: COLUMN match_result.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.id IS '主键';


--
-- Name: COLUMN match_result.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.create_time IS '创建时间';


--
-- Name: COLUMN match_result.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.update_time IS '修改时间';


--
-- Name: COLUMN match_result.match_information_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.match_information_id IS '比赛信息id';


--
-- Name: COLUMN match_result.team_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.team_id IS '团队id';


--
-- Name: COLUMN match_result.ranking; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.ranking IS '比赛名次';


--
-- Name: COLUMN match_result.increased_combat_effectiveness; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.increased_combat_effectiveness IS '提升的战斗力';


--
-- Name: match_result_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.match_result_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.match_result_id_seq OWNER TO postgres;

--
-- Name: match_result_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.match_result_id_seq OWNED BY galop.match_result.id;


--
-- Name: match_result_match_information_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.match_result_match_information_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.match_result_match_information_id_seq OWNER TO postgres;

--
-- Name: match_result_match_information_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.match_result_match_information_id_seq OWNED BY galop.match_result.match_information_id;


--
-- Name: match_result_team_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.match_result_team_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.match_result_team_id_seq OWNER TO postgres;

--
-- Name: match_result_team_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.match_result_team_id_seq OWNED BY galop.match_result.team_id;


--
-- Name: registration_information; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.registration_information (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    match_information_id bigint NOT NULL,
    team_id bigint NOT NULL,
    registration_status character varying(12) DEFAULT '审核'::character varying NOT NULL
);


ALTER TABLE galop.registration_information OWNER TO postgres;

--
-- Name: TABLE registration_information; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.registration_information IS '报名信息表';


--
-- Name: COLUMN registration_information.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_information.id IS '主键';


--
-- Name: COLUMN registration_information.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_information.create_time IS '创建时间';


--
-- Name: COLUMN registration_information.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_information.update_time IS '修改时间';


--
-- Name: COLUMN registration_information.match_information_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_information.match_information_id IS '比赛信息id';


--
-- Name: COLUMN registration_information.team_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_information.team_id IS '团队id';


--
-- Name: registration_information_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.registration_information_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.registration_information_id_seq OWNER TO postgres;

--
-- Name: registration_information_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.registration_information_id_seq OWNED BY galop.registration_information.id;


--
-- Name: registration_information_match_information_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.registration_information_match_information_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.registration_information_match_information_id_seq OWNER TO postgres;

--
-- Name: registration_information_match_information_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.registration_information_match_information_id_seq OWNED BY galop.registration_information.match_information_id;


--
-- Name: registration_information_team_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.registration_information_team_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.registration_information_team_id_seq OWNER TO postgres;

--
-- Name: registration_information_team_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.registration_information_team_id_seq OWNED BY galop.registration_information.team_id;


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
-- Name: team; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.team (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    team_name character varying(50) NOT NULL,
    total_team_fighting_power integer NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    team_state character varying(12) DEFAULT '审核'::character varying NOT NULL
);


ALTER TABLE galop.team OWNER TO postgres;

--
-- Name: TABLE team; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.team IS '组队表';


--
-- Name: COLUMN team.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.id IS '主键';


--
-- Name: COLUMN team.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.create_time IS '创建时间';


--
-- Name: COLUMN team.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.update_time IS '修改时间';


--
-- Name: COLUMN team.team_name; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.team_name IS '队名';


--
-- Name: COLUMN team.total_team_fighting_power; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.total_team_fighting_power IS '团队的总战斗力';


--
-- Name: COLUMN team.user_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.user_id IS '用户id';


--
-- Name: COLUMN team.role_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.role_id IS '角色id';


--
-- Name: COLUMN team.team_state; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.team_state IS '组队的状态';


--
-- Name: team_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.team_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.team_id_seq OWNER TO postgres;

--
-- Name: team_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.team_id_seq OWNED BY galop.team.id;


--
-- Name: team_role_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.team_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.team_role_id_seq OWNER TO postgres;

--
-- Name: team_role_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.team_role_id_seq OWNED BY galop.team.role_id;


--
-- Name: team_user_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.team_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.team_user_id_seq OWNER TO postgres;

--
-- Name: team_user_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.team_user_id_seq OWNED BY galop.team.user_id;


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
    user_portrait character varying(50) DEFAULT 'http://localhost:8080/avatar/avatar.png'::character varying NOT NULL,
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
-- Name: announcement id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.announcement ALTER COLUMN id SET DEFAULT nextval('galop.announcement_id_seq'::regclass);


--
-- Name: fighting_capacity id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity ALTER COLUMN id SET DEFAULT nextval('galop.fighting_capacity_id_seq'::regclass);


--
-- Name: fighting_capacity match_information_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity ALTER COLUMN match_information_id SET DEFAULT nextval('galop.fighting_capacity_match_information_id_seq'::regclass);


--
-- Name: fighting_capacity user_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity ALTER COLUMN user_id SET DEFAULT nextval('galop.fighting_capacity_user_id_seq'::regclass);


--
-- Name: match_information id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_information ALTER COLUMN id SET DEFAULT nextval('galop.match_information_id_seq'::regclass);


--
-- Name: match_result id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result ALTER COLUMN id SET DEFAULT nextval('galop.match_result_id_seq'::regclass);


--
-- Name: match_result match_information_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result ALTER COLUMN match_information_id SET DEFAULT nextval('galop.match_result_match_information_id_seq'::regclass);


--
-- Name: match_result team_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result ALTER COLUMN team_id SET DEFAULT nextval('galop.match_result_team_id_seq'::regclass);


--
-- Name: registration_information id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.registration_information ALTER COLUMN id SET DEFAULT nextval('galop.registration_information_id_seq'::regclass);


--
-- Name: registration_information match_information_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.registration_information ALTER COLUMN match_information_id SET DEFAULT nextval('galop.registration_information_match_information_id_seq'::regclass);


--
-- Name: registration_information team_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.registration_information ALTER COLUMN team_id SET DEFAULT nextval('galop.registration_information_team_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.role ALTER COLUMN id SET DEFAULT nextval('galop.role_id_seq'::regclass);


--
-- Name: team id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team ALTER COLUMN id SET DEFAULT nextval('galop.team_id_seq'::regclass);


--
-- Name: team user_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team ALTER COLUMN user_id SET DEFAULT nextval('galop.team_user_id_seq'::regclass);


--
-- Name: team role_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team ALTER COLUMN role_id SET DEFAULT nextval('galop.team_role_id_seq'::regclass);


--
-- Name: user role_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."user" ALTER COLUMN role_id SET DEFAULT nextval('galop.user_role_id_seq'::regclass);


--
-- Data for Name: announcement; Type: TABLE DATA; Schema: galop; Owner: postgres
--

COPY galop.announcement (id, create_time, update_time, announcement_name, announcement_content) FROM stdin;
\.


--
-- Data for Name: fighting_capacity; Type: TABLE DATA; Schema: galop; Owner: postgres
--

COPY galop.fighting_capacity (id, create_time, update_time, match_information_id, increased_combat_effectiveness, user_id) FROM stdin;
\.


--
-- Data for Name: match_information; Type: TABLE DATA; Schema: galop; Owner: postgres
--

COPY galop.match_information (id, create_time, update_time, match_name, team_size, first_place_combat_effectiveness, decrement_parameter) FROM stdin;
\.


--
-- Data for Name: match_result; Type: TABLE DATA; Schema: galop; Owner: postgres
--

COPY galop.match_result (id, create_time, update_time, match_information_id, team_id, ranking, increased_combat_effectiveness) FROM stdin;
\.


--
-- Data for Name: registration_information; Type: TABLE DATA; Schema: galop; Owner: postgres
--

COPY galop.registration_information (id, create_time, update_time, match_information_id, team_id, registration_status) FROM stdin;
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: galop; Owner: postgres
--

COPY galop.role (id, create_time, update_time, role_name) FROM stdin;
1	2020-02-29 07:54:39.726039	2020-03-02 12:55:08.109629	老师
2	2020-03-12 16:31:15.609138	2020-03-12 16:31:15.609138	学生
\.


--
-- Data for Name: team; Type: TABLE DATA; Schema: galop; Owner: postgres
--

COPY galop.team (id, create_time, update_time, team_name, total_team_fighting_power, user_id, role_id, team_state) FROM stdin;
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: galop; Owner: postgres
--

COPY galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, user_fighting_capacity, role_id) FROM stdin;
2	2020-03-12 16:31:28.541473	2020-03-17 15:58:42.356913	201620205052	张天赐	$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om	\N	http://localhost:8080/avatar/avatar.png	0	2
1	2020-03-12 16:32:23.802433	2020-03-17 18:37:45.647911	admin	老师	$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om	\N	http://localhost:8080/avatar/avatar.png	0	1
\.


--
-- Name: announcement_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.announcement_id_seq', 1, false);


--
-- Name: fighting_capacity_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.fighting_capacity_id_seq', 1, false);


--
-- Name: fighting_capacity_match_information_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.fighting_capacity_match_information_id_seq', 1, false);


--
-- Name: fighting_capacity_user_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.fighting_capacity_user_id_seq', 1, false);


--
-- Name: match_information_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.match_information_id_seq', 1, false);


--
-- Name: match_result_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.match_result_id_seq', 1, false);


--
-- Name: match_result_match_information_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.match_result_match_information_id_seq', 1, false);


--
-- Name: match_result_team_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.match_result_team_id_seq', 1, false);


--
-- Name: registration_information_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.registration_information_id_seq', 1, false);


--
-- Name: registration_information_match_information_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.registration_information_match_information_id_seq', 1, false);


--
-- Name: registration_information_team_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.registration_information_team_id_seq', 1, false);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.role_id_seq', 2, true);


--
-- Name: team_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.team_id_seq', 1, false);


--
-- Name: team_role_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.team_role_id_seq', 1, false);


--
-- Name: team_user_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.team_user_id_seq', 1, false);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.user_id_seq', 45, true);


--
-- Name: user_role_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.user_role_id_seq', 1, false);


--
-- Name: announcement announcement_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.announcement
    ADD CONSTRAINT announcement_pk PRIMARY KEY (id);


--
-- Name: fighting_capacity fighting_capacity_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity
    ADD CONSTRAINT fighting_capacity_pk PRIMARY KEY (id);


--
-- Name: match_information match_information_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_information
    ADD CONSTRAINT match_information_pk PRIMARY KEY (id);


--
-- Name: match_result match_result_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result
    ADD CONSTRAINT match_result_pk PRIMARY KEY (id);


--
-- Name: registration_information registration_information_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.registration_information
    ADD CONSTRAINT registration_information_pk PRIMARY KEY (id);


--
-- Name: role role_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.role
    ADD CONSTRAINT role_pk PRIMARY KEY (id);


--
-- Name: team team_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team
    ADD CONSTRAINT team_pk PRIMARY KEY (id);


--
-- Name: user user_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- Name: fighting_capacity_match_information_id_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX fighting_capacity_match_information_id_uindex ON galop.fighting_capacity USING btree (match_information_id);


--
-- Name: fighting_capacity_user_id_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX fighting_capacity_user_id_uindex ON galop.fighting_capacity USING btree (user_id);


--
-- Name: match_result_match_information_id_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX match_result_match_information_id_uindex ON galop.match_result USING btree (match_information_id);


--
-- Name: match_result_team_id_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX match_result_team_id_uindex ON galop.match_result USING btree (team_id);


--
-- Name: registration_information_match_information_id_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX registration_information_match_information_id_uindex ON galop.registration_information USING btree (match_information_id);


--
-- Name: registration_information_team_id_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX registration_information_team_id_uindex ON galop.registration_information USING btree (team_id);


--
-- Name: role_role_name_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX role_role_name_uindex ON galop.role USING btree (role_name);


--
-- Name: team_role_id_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX team_role_id_uindex ON galop.team USING btree (role_id);


--
-- Name: team_user_id_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX team_user_id_uindex ON galop.team USING btree (user_id);


--
-- Name: user_user_number_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX user_user_number_uindex ON galop."user" USING btree (user_number);


--
-- Name: announcement modification_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER modification_time BEFORE UPDATE ON galop.announcement FOR EACH ROW EXECUTE FUNCTION galop.modification_time();


--
-- Name: TRIGGER modification_time ON announcement; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER modification_time ON galop.announcement IS '修改时间';


--
-- Name: fighting_capacity modification_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER modification_time BEFORE UPDATE ON galop.fighting_capacity FOR EACH ROW EXECUTE FUNCTION galop.modification_time();


--
-- Name: TRIGGER modification_time ON fighting_capacity; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER modification_time ON galop.fighting_capacity IS '修改时间';


--
-- Name: match_information modification_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER modification_time BEFORE UPDATE ON galop.match_information FOR EACH ROW EXECUTE FUNCTION galop.modification_time();


--
-- Name: TRIGGER modification_time ON match_information; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER modification_time ON galop.match_information IS '修改时间';


--
-- Name: match_result modification_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER modification_time BEFORE UPDATE ON galop.match_result FOR EACH ROW EXECUTE FUNCTION galop.modification_time();


--
-- Name: TRIGGER modification_time ON match_result; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER modification_time ON galop.match_result IS '修改时间';


--
-- Name: registration_information modification_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER modification_time BEFORE UPDATE ON galop.registration_information FOR EACH ROW EXECUTE FUNCTION galop.modification_time();


--
-- Name: TRIGGER modification_time ON registration_information; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER modification_time ON galop.registration_information IS '修改时间';


--
-- Name: role modification_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER modification_time BEFORE UPDATE ON galop.role FOR EACH ROW EXECUTE FUNCTION galop.modification_time();


--
-- Name: TRIGGER modification_time ON role; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER modification_time ON galop.role IS '修改时间';


--
-- Name: team modification_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER modification_time BEFORE UPDATE ON galop.team FOR EACH ROW EXECUTE FUNCTION galop.modification_time();


--
-- Name: TRIGGER modification_time ON team; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER modification_time ON galop.team IS '修改时间';


--
-- Name: user modification_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER modification_time BEFORE UPDATE ON galop."user" FOR EACH ROW EXECUTE FUNCTION galop.modification_time();


--
-- Name: TRIGGER modification_time ON "user"; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER modification_time ON galop."user" IS '修改时间';


--
-- Name: fighting_capacity fighting_capacity_match_information_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity
    ADD CONSTRAINT fighting_capacity_match_information_id_fk FOREIGN KEY (match_information_id) REFERENCES galop.match_information(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: fighting_capacity fighting_capacity_user_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity
    ADD CONSTRAINT fighting_capacity_user_id_fk FOREIGN KEY (user_id) REFERENCES galop."user"(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: match_result match_result_match_information_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result
    ADD CONSTRAINT match_result_match_information_id_fk FOREIGN KEY (match_information_id) REFERENCES galop.match_information(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: match_result match_result_team_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result
    ADD CONSTRAINT match_result_team_id_fk FOREIGN KEY (team_id) REFERENCES galop.team(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: registration_information registration_information_match_information_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.registration_information
    ADD CONSTRAINT registration_information_match_information_id_fk FOREIGN KEY (match_information_id) REFERENCES galop.match_information(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: registration_information registration_information_team_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.registration_information
    ADD CONSTRAINT registration_information_team_id_fk FOREIGN KEY (team_id) REFERENCES galop.team(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: team team_role_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team
    ADD CONSTRAINT team_role_id_fk FOREIGN KEY (role_id) REFERENCES galop.role(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: team team_user_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team
    ADD CONSTRAINT team_user_id_fk FOREIGN KEY (user_id) REFERENCES galop."user"(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: user user_role_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."user"
    ADD CONSTRAINT user_role_id_fk FOREIGN KEY (role_id) REFERENCES galop.role(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

