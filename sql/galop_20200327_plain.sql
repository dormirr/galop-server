--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-03-27 22:28:12

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

DROP DATABASE galop;
--
-- TOC entry 2981 (class 1262 OID 16577)
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
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 2981
-- Name: DATABASE galop; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE galop IS '学科竞赛训练管理系统数据库';


--
-- TOC entry 7 (class 2615 OID 16578)
-- Name: galop; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA galop;


ALTER SCHEMA galop OWNER TO postgres;

--
-- TOC entry 2983 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA galop; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA galop IS '学科竞赛训练管理系统数据库';


--
-- TOC entry 227 (class 1255 OID 16580)
-- Name: update_time(); Type: FUNCTION; Schema: galop; Owner: postgres
--

CREATE FUNCTION galop.update_time() RETURNS trigger
    LANGUAGE plpgsql STRICT SECURITY DEFINER LEAKPROOF
    AS $$begin
    new.update_time = current_timestamp;
    return new;
end$$;


ALTER FUNCTION galop.update_time() OWNER TO postgres;

--
-- TOC entry 2984 (class 0 OID 0)
-- Dependencies: 227
-- Name: FUNCTION update_time(); Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON FUNCTION galop.update_time() IS '更新时间';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16681)
-- Name: announcement; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.announcement (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    announcement_title text NOT NULL,
    announcement_content text NOT NULL
);


ALTER TABLE galop.announcement OWNER TO postgres;

--
-- TOC entry 2985 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE announcement; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.announcement IS '公告表';


--
-- TOC entry 2986 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN announcement.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.announcement.id IS '主键';


--
-- TOC entry 2987 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN announcement.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.announcement.create_time IS '创建时间';


--
-- TOC entry 2988 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN announcement.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.announcement.update_time IS '更新时间';


--
-- TOC entry 2989 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN announcement.announcement_title; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.announcement.announcement_title IS '公告标题';


--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 217
-- Name: COLUMN announcement.announcement_content; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.announcement.announcement_content IS '公告内容';


--
-- TOC entry 216 (class 1259 OID 16679)
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
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 216
-- Name: announcement_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.announcement_id_seq OWNED BY galop.announcement.id;


--
-- TOC entry 209 (class 1259 OID 16641)
-- Name: fighting_capacity; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.fighting_capacity (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    match_info_id bigint NOT NULL,
    reward integer NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE galop.fighting_capacity OWNER TO postgres;

--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 209
-- Name: TABLE fighting_capacity; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.fighting_capacity IS '积分表';


--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN fighting_capacity.match_info_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.fighting_capacity.match_info_id IS '比赛信息 ID';


--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN fighting_capacity.reward; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.fighting_capacity.reward IS '得到奖励';


--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN fighting_capacity.user_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.fighting_capacity.user_id IS '用户 ID';


--
-- TOC entry 208 (class 1259 OID 16639)
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
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 208
-- Name: fighting_capacity_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.fighting_capacity_id_seq OWNED BY galop.fighting_capacity.id;


--
-- TOC entry 219 (class 1259 OID 16714)
-- Name: fighting_capacity_match_info_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.fighting_capacity_match_info_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.fighting_capacity_match_info_id_seq OWNER TO postgres;

--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 219
-- Name: fighting_capacity_match_info_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.fighting_capacity_match_info_id_seq OWNED BY galop.fighting_capacity.match_info_id;


--
-- TOC entry 220 (class 1259 OID 16721)
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
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 220
-- Name: fighting_capacity_user_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.fighting_capacity_user_id_seq OWNED BY galop.fighting_capacity.user_id;


--
-- TOC entry 207 (class 1259 OID 16623)
-- Name: match_info; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.match_info (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    match_name character varying(80) NOT NULL,
    team_size integer DEFAULT 1 NOT NULL,
    champion_award integer NOT NULL,
    decrement_parameter integer NOT NULL
);


ALTER TABLE galop.match_info OWNER TO postgres;

--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 207
-- Name: TABLE match_info; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.match_info IS '比赛信息表';


--
-- TOC entry 3000 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN match_info.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_info.id IS '主键';


--
-- TOC entry 3001 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN match_info.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_info.create_time IS '创建时间';


--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN match_info.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_info.update_time IS '更新时间';


--
-- TOC entry 3003 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN match_info.match_name; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_info.match_name IS '比赛名';


--
-- TOC entry 3004 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN match_info.team_size; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_info.team_size IS '团队规模';


--
-- TOC entry 3005 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN match_info.champion_award; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_info.champion_award IS '冠军奖励';


--
-- TOC entry 3006 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN match_info.decrement_parameter; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_info.decrement_parameter IS '递减梯度';


--
-- TOC entry 206 (class 1259 OID 16621)
-- Name: match_info_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.match_info_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.match_info_id_seq OWNER TO postgres;

--
-- TOC entry 3007 (class 0 OID 0)
-- Dependencies: 206
-- Name: match_info_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.match_info_id_seq OWNED BY galop.match_info.id;


--
-- TOC entry 215 (class 1259 OID 16671)
-- Name: match_result; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.match_result (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    match_info_id bigint NOT NULL,
    user_id bigint NOT NULL,
    ranking integer NOT NULL,
    reward integer NOT NULL
);


ALTER TABLE galop.match_result OWNER TO postgres;

--
-- TOC entry 3008 (class 0 OID 0)
-- Dependencies: 215
-- Name: TABLE match_result; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.match_result IS '比赛结果表';


--
-- TOC entry 3009 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.id IS '主键';


--
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.create_time IS '创建时间';


--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.update_time IS '更新时间';


--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.match_info_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.match_info_id IS '比赛信息 ID';


--
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.user_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.user_id IS '用户 ID';


--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.ranking; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.ranking IS '比赛名次';


--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.reward; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.reward IS '得到奖励';


--
-- TOC entry 214 (class 1259 OID 16669)
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
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 214
-- Name: match_result_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.match_result_id_seq OWNED BY galop.match_result.id;


--
-- TOC entry 225 (class 1259 OID 16815)
-- Name: match_result_match_info_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.match_result_match_info_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.match_result_match_info_id_seq OWNER TO postgres;

--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 225
-- Name: match_result_match_info_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.match_result_match_info_id_seq OWNED BY galop.match_result.match_info_id;


--
-- TOC entry 226 (class 1259 OID 16822)
-- Name: match_result_user_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.match_result_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.match_result_user_id_seq OWNER TO postgres;

--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 226
-- Name: match_result_user_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.match_result_user_id_seq OWNED BY galop.match_result.user_id;


--
-- TOC entry 213 (class 1259 OID 16661)
-- Name: registration_info; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.registration_info (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    match_info_id bigint NOT NULL,
    team_id bigint NOT NULL,
    registration_status character varying(8) DEFAULT '审核'::character varying NOT NULL
);


ALTER TABLE galop.registration_info OWNER TO postgres;

--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 213
-- Name: TABLE registration_info; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.registration_info IS '报名信息表';


--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN registration_info.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_info.id IS '主键';


--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN registration_info.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_info.create_time IS '创建时间';


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN registration_info.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_info.update_time IS '更新时间';


--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN registration_info.match_info_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_info.match_info_id IS '比赛信息 ID';


--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN registration_info.team_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_info.team_id IS '团队 ID';


--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN registration_info.registration_status; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_info.registration_status IS '报名状态';


--
-- TOC entry 212 (class 1259 OID 16659)
-- Name: registration_info_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.registration_info_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.registration_info_id_seq OWNER TO postgres;

--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 212
-- Name: registration_info_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.registration_info_id_seq OWNED BY galop.registration_info.id;


--
-- TOC entry 223 (class 1259 OID 16787)
-- Name: registration_info_match_info_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.registration_info_match_info_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.registration_info_match_info_id_seq OWNER TO postgres;

--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 223
-- Name: registration_info_match_info_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.registration_info_match_info_id_seq OWNED BY galop.registration_info.match_info_id;


--
-- TOC entry 224 (class 1259 OID 16794)
-- Name: registration_info_team_id_seq; Type: SEQUENCE; Schema: galop; Owner: postgres
--

CREATE SEQUENCE galop.registration_info_team_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE galop.registration_info_team_id_seq OWNER TO postgres;

--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 224
-- Name: registration_info_team_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.registration_info_team_id_seq OWNED BY galop.registration_info.team_id;


--
-- TOC entry 202 (class 1259 OID 16581)
-- Name: role; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.role (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    role_name character varying(8) NOT NULL
);


ALTER TABLE galop.role OWNER TO postgres;

--
-- TOC entry 3029 (class 0 OID 0)
-- Dependencies: 202
-- Name: TABLE role; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.role IS '角色表';


--
-- TOC entry 3030 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN role.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.role.id IS '主键';


--
-- TOC entry 3031 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN role.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.role.create_time IS '创建时间';


--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN role.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.role.update_time IS '更新时间';


--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN role.role_name; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.role.role_name IS '角色名';


--
-- TOC entry 203 (class 1259 OID 16584)
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
-- TOC entry 3034 (class 0 OID 0)
-- Dependencies: 203
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.role_id_seq OWNED BY galop.role.id;


--
-- TOC entry 211 (class 1259 OID 16651)
-- Name: team; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop.team (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    team_name character varying(48) NOT NULL,
    team_profile text,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    team_state character varying(8) DEFAULT '审核'::character varying NOT NULL,
    team_fighting_capacity integer NOT NULL
);


ALTER TABLE galop.team OWNER TO postgres;

--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 211
-- Name: TABLE team; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.team IS '团队表';


--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.id IS '主键';


--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.create_time IS '创建时间';


--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.update_time IS '更新时间';


--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.team_name; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.team_name IS '团队名';


--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.team_profile; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.team_profile IS '简介';


--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.user_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.user_id IS '用户 ID';


--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.role_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.role_id IS '角色 ID';


--
-- TOC entry 3043 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.team_state; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.team_state IS '我的团队状态';


--
-- TOC entry 3044 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.team_fighting_capacity; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.team_fighting_capacity IS '团队总战斗力';


--
-- TOC entry 210 (class 1259 OID 16649)
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
-- TOC entry 3045 (class 0 OID 0)
-- Dependencies: 210
-- Name: team_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.team_id_seq OWNED BY galop.team.id;


--
-- TOC entry 222 (class 1259 OID 16751)
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
-- TOC entry 3046 (class 0 OID 0)
-- Dependencies: 222
-- Name: team_role_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.team_role_id_seq OWNED BY galop.team.role_id;


--
-- TOC entry 221 (class 1259 OID 16741)
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
-- TOC entry 3047 (class 0 OID 0)
-- Dependencies: 221
-- Name: team_user_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.team_user_id_seq OWNED BY galop.team.user_id;


--
-- TOC entry 205 (class 1259 OID 16613)
-- Name: userEntity; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop."userEntity" (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    user_number character varying(12) NOT NULL,
    user_name character varying(24) NOT NULL,
    user_password character varying(172) DEFAULT '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om'::character varying NOT NULL,
    user_email character varying(320),
    user_portrait character varying(2083) DEFAULT 'http://localhost:8080/avatar/avatar.png'::character varying NOT NULL,
    role_id bigint NOT NULL,
    user_fighting_capacity integer DEFAULT 0 NOT NULL
);


ALTER TABLE galop."userEntity" OWNER TO postgres;

--
-- TOC entry 3048 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE "userEntity"; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop."userEntity" IS '用户表';


--
-- TOC entry 3049 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "userEntity".id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."userEntity".id IS '主键';


--
-- TOC entry 3050 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "userEntity".create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."userEntity".create_time IS '创建时间';


--
-- TOC entry 3051 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "userEntity".update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."userEntity".update_time IS '更新时间';


--
-- TOC entry 3052 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "userEntity".user_number; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."userEntity".user_number IS '账号（学号/工号）';


--
-- TOC entry 3053 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "userEntity".user_name; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."userEntity".user_name IS '姓名';


--
-- TOC entry 3054 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "userEntity".user_password; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."userEntity".user_password IS '密码';


--
-- TOC entry 3055 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "userEntity".user_email; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."userEntity".user_email IS '邮箱';


--
-- TOC entry 3056 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "userEntity".role_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."userEntity".role_id IS '角色 ID';


--
-- TOC entry 3057 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "userEntity".user_fighting_capacity; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."userEntity".user_fighting_capacity IS '战斗力';


--
-- TOC entry 204 (class 1259 OID 16611)
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
-- TOC entry 3058 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.user_id_seq OWNED BY galop."userEntity".id;


--
-- TOC entry 218 (class 1259 OID 16695)
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
-- TOC entry 3059 (class 0 OID 0)
-- Dependencies: 218
-- Name: user_role_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.user_role_id_seq OWNED BY galop."userEntity".role_id;


--
-- TOC entry 2787 (class 2604 OID 16684)
-- Name: announcement id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.announcement ALTER COLUMN id SET DEFAULT nextval('galop.announcement_id_seq'::regclass);


--
-- TOC entry 2765 (class 2604 OID 16644)
-- Name: fighting_capacity id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity ALTER COLUMN id SET DEFAULT nextval('galop.fighting_capacity_id_seq'::regclass);


--
-- TOC entry 2768 (class 2604 OID 16716)
-- Name: fighting_capacity match_info_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity ALTER COLUMN match_info_id SET DEFAULT nextval('galop.fighting_capacity_match_info_id_seq'::regclass);


--
-- TOC entry 2769 (class 2604 OID 16723)
-- Name: fighting_capacity user_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity ALTER COLUMN user_id SET DEFAULT nextval('galop.fighting_capacity_user_id_seq'::regclass);


--
-- TOC entry 2761 (class 2604 OID 16626)
-- Name: match_info id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_info ALTER COLUMN id SET DEFAULT nextval('galop.match_info_id_seq'::regclass);


--
-- TOC entry 2782 (class 2604 OID 16674)
-- Name: match_result id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result ALTER COLUMN id SET DEFAULT nextval('galop.match_result_id_seq'::regclass);


--
-- TOC entry 2785 (class 2604 OID 16817)
-- Name: match_result match_info_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result ALTER COLUMN match_info_id SET DEFAULT nextval('galop.match_result_match_info_id_seq'::regclass);


--
-- TOC entry 2786 (class 2604 OID 16824)
-- Name: match_result user_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result ALTER COLUMN user_id SET DEFAULT nextval('galop.match_result_user_id_seq'::regclass);


--
-- TOC entry 2776 (class 2604 OID 16664)
-- Name: registration_info id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.registration_info ALTER COLUMN id SET DEFAULT nextval('galop.registration_info_id_seq'::regclass);


--
-- TOC entry 2779 (class 2604 OID 16789)
-- Name: registration_info match_info_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.registration_info ALTER COLUMN match_info_id SET DEFAULT nextval('galop.registration_info_match_info_id_seq'::regclass);


--
-- TOC entry 2780 (class 2604 OID 16796)
-- Name: registration_info team_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.registration_info ALTER COLUMN team_id SET DEFAULT nextval('galop.registration_info_team_id_seq'::regclass);


--
-- TOC entry 2751 (class 2604 OID 16586)
-- Name: role id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.role ALTER COLUMN id SET DEFAULT nextval('galop.role_id_seq'::regclass);


--
-- TOC entry 2770 (class 2604 OID 16654)
-- Name: team id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team ALTER COLUMN id SET DEFAULT nextval('galop.team_id_seq'::regclass);


--
-- TOC entry 2773 (class 2604 OID 16743)
-- Name: team user_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team ALTER COLUMN user_id SET DEFAULT nextval('galop.team_user_id_seq'::regclass);


--
-- TOC entry 2774 (class 2604 OID 16753)
-- Name: team role_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team ALTER COLUMN role_id SET DEFAULT nextval('galop.team_role_id_seq'::regclass);


--
-- TOC entry 2754 (class 2604 OID 16616)
-- Name: userEntity id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."userEntity" ALTER COLUMN id SET DEFAULT nextval('galop.user_id_seq'::regclass);


--
-- TOC entry 2759 (class 2604 OID 16697)
-- Name: userEntity role_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."userEntity" ALTER COLUMN role_id SET DEFAULT nextval('galop.user_role_id_seq'::regclass);


--
-- TOC entry 2966 (class 0 OID 16681)
-- Dependencies: 217
-- Data for Name: announcement; Type: TABLE DATA; Schema: galop; Owner: postgres
--



--
-- TOC entry 2958 (class 0 OID 16641)
-- Dependencies: 209
-- Data for Name: fighting_capacity; Type: TABLE DATA; Schema: galop; Owner: postgres
--



--
-- TOC entry 2956 (class 0 OID 16623)
-- Dependencies: 207
-- Data for Name: match_info; Type: TABLE DATA; Schema: galop; Owner: postgres
--



--
-- TOC entry 2964 (class 0 OID 16671)
-- Dependencies: 215
-- Data for Name: match_result; Type: TABLE DATA; Schema: galop; Owner: postgres
--



--
-- TOC entry 2962 (class 0 OID 16661)
-- Dependencies: 213
-- Data for Name: registration_info; Type: TABLE DATA; Schema: galop; Owner: postgres
--



--
-- TOC entry 2951 (class 0 OID 16581)
-- Dependencies: 202
-- Data for Name: role; Type: TABLE DATA; Schema: galop; Owner: postgres
--



--
-- TOC entry 2960 (class 0 OID 16651)
-- Dependencies: 211
-- Data for Name: team; Type: TABLE DATA; Schema: galop; Owner: postgres
--



--
-- TOC entry 2954 (class 0 OID 16613)
-- Dependencies: 205
-- Data for Name: userEntity; Type: TABLE DATA; Schema: galop; Owner: postgres
--



--
-- TOC entry 3060 (class 0 OID 0)
-- Dependencies: 216
-- Name: announcement_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.announcement_id_seq', 1, false);


--
-- TOC entry 3061 (class 0 OID 0)
-- Dependencies: 208
-- Name: fighting_capacity_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.fighting_capacity_id_seq', 1, false);


--
-- TOC entry 3062 (class 0 OID 0)
-- Dependencies: 219
-- Name: fighting_capacity_match_info_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.fighting_capacity_match_info_id_seq', 1, false);


--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 220
-- Name: fighting_capacity_user_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.fighting_capacity_user_id_seq', 1, false);


--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 206
-- Name: match_info_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.match_info_id_seq', 1, false);


--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 214
-- Name: match_result_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.match_result_id_seq', 1, false);


--
-- TOC entry 3066 (class 0 OID 0)
-- Dependencies: 225
-- Name: match_result_match_info_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.match_result_match_info_id_seq', 1, false);


--
-- TOC entry 3067 (class 0 OID 0)
-- Dependencies: 226
-- Name: match_result_user_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.match_result_user_id_seq', 1, false);


--
-- TOC entry 3068 (class 0 OID 0)
-- Dependencies: 212
-- Name: registration_info_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.registration_info_id_seq', 1, false);


--
-- TOC entry 3069 (class 0 OID 0)
-- Dependencies: 223
-- Name: registration_info_match_info_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.registration_info_match_info_id_seq', 1, false);


--
-- TOC entry 3070 (class 0 OID 0)
-- Dependencies: 224
-- Name: registration_info_team_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.registration_info_team_id_seq', 1, false);


--
-- TOC entry 3071 (class 0 OID 0)
-- Dependencies: 203
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.role_id_seq', 1, false);


--
-- TOC entry 3072 (class 0 OID 0)
-- Dependencies: 210
-- Name: team_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.team_id_seq', 1, false);


--
-- TOC entry 3073 (class 0 OID 0)
-- Dependencies: 222
-- Name: team_role_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.team_role_id_seq', 1, false);


--
-- TOC entry 3074 (class 0 OID 0)
-- Dependencies: 221
-- Name: team_user_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.team_user_id_seq', 1, false);


--
-- TOC entry 3075 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.user_id_seq', 1, false);


--
-- TOC entry 3076 (class 0 OID 0)
-- Dependencies: 218
-- Name: user_role_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.user_role_id_seq', 1, false);


--
-- TOC entry 2807 (class 2606 OID 16688)
-- Name: announcement announcement_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.announcement
    ADD CONSTRAINT announcement_pk PRIMARY KEY (id);


--
-- TOC entry 2799 (class 2606 OID 16648)
-- Name: fighting_capacity fighting_capacity_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity
    ADD CONSTRAINT fighting_capacity_pk PRIMARY KEY (id);


--
-- TOC entry 2797 (class 2606 OID 16630)
-- Name: match_info match_info_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_info
    ADD CONSTRAINT match_info_pk PRIMARY KEY (id);


--
-- TOC entry 2805 (class 2606 OID 16678)
-- Name: match_result match_result_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result
    ADD CONSTRAINT match_result_pk PRIMARY KEY (id);


--
-- TOC entry 2803 (class 2606 OID 16668)
-- Name: registration_info registration_info_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.registration_info
    ADD CONSTRAINT registration_info_pk PRIMARY KEY (id);


--
-- TOC entry 2792 (class 2606 OID 16594)
-- Name: role role_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.role
    ADD CONSTRAINT role_pk PRIMARY KEY (id);


--
-- TOC entry 2801 (class 2606 OID 16657)
-- Name: team team_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team
    ADD CONSTRAINT team_pk PRIMARY KEY (id);


--
-- TOC entry 2794 (class 2606 OID 16620)
-- Name: userEntity user_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."userEntity"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- TOC entry 2790 (class 1259 OID 16592)
-- Name: role_id_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX role_id_uindex ON galop.role USING btree (id);


--
-- TOC entry 2795 (class 1259 OID 16689)
-- Name: user_user_number_uindex; Type: INDEX; Schema: galop; Owner: postgres
--

CREATE UNIQUE INDEX user_user_number_uindex ON galop."userEntity" USING btree (user_number);


--
-- TOC entry 2824 (class 2620 OID 16779)
-- Name: announcement update_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER update_time BEFORE UPDATE ON galop.announcement FOR EACH ROW EXECUTE FUNCTION galop.update_time();


--
-- TOC entry 3077 (class 0 OID 0)
-- Dependencies: 2824
-- Name: TRIGGER update_time ON announcement; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER update_time ON galop.announcement IS '更新时间';


--
-- TOC entry 2820 (class 2620 OID 16778)
-- Name: fighting_capacity update_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER update_time BEFORE UPDATE ON galop.fighting_capacity FOR EACH ROW EXECUTE FUNCTION galop.update_time();


--
-- TOC entry 3078 (class 0 OID 0)
-- Dependencies: 2820
-- Name: TRIGGER update_time ON fighting_capacity; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER update_time ON galop.fighting_capacity IS '更新时间';


--
-- TOC entry 2819 (class 2620 OID 16777)
-- Name: match_info update_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER update_time BEFORE UPDATE ON galop.match_info FOR EACH ROW EXECUTE FUNCTION galop.update_time();


--
-- TOC entry 3079 (class 0 OID 0)
-- Dependencies: 2819
-- Name: TRIGGER update_time ON match_info; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER update_time ON galop.match_info IS '更新时间';


--
-- TOC entry 2823 (class 2620 OID 16776)
-- Name: match_result update_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER update_time BEFORE UPDATE ON galop.match_result FOR EACH ROW EXECUTE FUNCTION galop.update_time();


--
-- TOC entry 3080 (class 0 OID 0)
-- Dependencies: 2823
-- Name: TRIGGER update_time ON match_result; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER update_time ON galop.match_result IS '更新时间';


--
-- TOC entry 2822 (class 2620 OID 16775)
-- Name: registration_info update_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER update_time BEFORE UPDATE ON galop.registration_info FOR EACH ROW EXECUTE FUNCTION galop.update_time();


--
-- TOC entry 3081 (class 0 OID 0)
-- Dependencies: 2822
-- Name: TRIGGER update_time ON registration_info; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER update_time ON galop.registration_info IS '更新时间';


--
-- TOC entry 2817 (class 2620 OID 16774)
-- Name: role update_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER update_time BEFORE UPDATE ON galop.role FOR EACH ROW EXECUTE FUNCTION galop.update_time();


--
-- TOC entry 3082 (class 0 OID 0)
-- Dependencies: 2817
-- Name: TRIGGER update_time ON role; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER update_time ON galop.role IS '更新时间';


--
-- TOC entry 2821 (class 2620 OID 16773)
-- Name: team update_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER update_time BEFORE UPDATE ON galop.team FOR EACH ROW EXECUTE FUNCTION galop.update_time();


--
-- TOC entry 3083 (class 0 OID 0)
-- Dependencies: 2821
-- Name: TRIGGER update_time ON team; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER update_time ON galop.team IS '更新时间';


--
-- TOC entry 2818 (class 2620 OID 16772)
-- Name: userEntity update_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER update_time BEFORE UPDATE ON galop."userEntity" FOR EACH ROW EXECUTE FUNCTION galop.update_time();


--
-- TOC entry 3084 (class 0 OID 0)
-- Dependencies: 2818
-- Name: TRIGGER update_time ON "userEntity"; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER update_time ON galop."userEntity" IS '更新时间';


--
-- TOC entry 2809 (class 2606 OID 16728)
-- Name: fighting_capacity fighting_capacity_match_info_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity
    ADD CONSTRAINT fighting_capacity_match_info_id_fk FOREIGN KEY (match_info_id) REFERENCES galop.match_info(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2810 (class 2606 OID 16733)
-- Name: fighting_capacity fighting_capacity_user_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity
    ADD CONSTRAINT fighting_capacity_user_id_fk FOREIGN KEY (user_id) REFERENCES galop."userEntity"(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2815 (class 2606 OID 16829)
-- Name: match_result match_result_match_info_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result
    ADD CONSTRAINT match_result_match_info_id_fk FOREIGN KEY (match_info_id) REFERENCES galop.match_info(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2816 (class 2606 OID 16834)
-- Name: match_result match_result_user_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result
    ADD CONSTRAINT match_result_user_id_fk FOREIGN KEY (user_id) REFERENCES galop."userEntity"(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2813 (class 2606 OID 16802)
-- Name: registration_info registration_info_match_info_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.registration_info
    ADD CONSTRAINT registration_info_match_info_id_fk FOREIGN KEY (match_info_id) REFERENCES galop.match_info(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2814 (class 2606 OID 16807)
-- Name: registration_info registration_info_team_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.registration_info
    ADD CONSTRAINT registration_info_team_id_fk FOREIGN KEY (team_id) REFERENCES galop.team(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2811 (class 2606 OID 16762)
-- Name: team team_role_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team
    ADD CONSTRAINT team_role_id_fk FOREIGN KEY (role_id) REFERENCES galop.role(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2812 (class 2606 OID 16767)
-- Name: team team_user_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team
    ADD CONSTRAINT team_user_id_fk FOREIGN KEY (user_id) REFERENCES galop."userEntity"(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2808 (class 2606 OID 16707)
-- Name: userEntity user_role_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."userEntity"
    ADD CONSTRAINT user_role_id_fk FOREIGN KEY (role_id) REFERENCES galop.role(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


-- Completed on 2020-03-27 22:28:13

--
-- PostgreSQL database dump complete
--

