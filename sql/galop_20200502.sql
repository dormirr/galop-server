--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-05-02 11:34:06

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
    team_size integer NOT NULL,
    champion_award integer NOT NULL,
    decrement_parameter integer NOT NULL,
    start_time timestamp without time zone NOT NULL,
    end_time timestamp without time zone NOT NULL,
    match_type character varying(20) NOT NULL
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
-- TOC entry 3007 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN match_info.start_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_info.start_time IS '比赛开始时间';


--
-- TOC entry 3008 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN match_info.end_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_info.end_time IS '比赛结束时间';


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
-- TOC entry 3009 (class 0 OID 0)
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
    team_id bigint NOT NULL,
    ranking integer NOT NULL
);


ALTER TABLE galop.match_result OWNER TO postgres;

--
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 215
-- Name: TABLE match_result; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.match_result IS '比赛结果表';


--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.id IS '主键';


--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.create_time IS '创建时间';


--
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.update_time IS '更新时间';


--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.match_info_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.match_info_id IS '比赛信息 ID';


--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.team_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.team_id IS '团队 ID';


--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 215
-- Name: COLUMN match_result.ranking; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.match_result.ranking IS '比赛名次';


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
-- TOC entry 3017 (class 0 OID 0)
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
-- TOC entry 3018 (class 0 OID 0)
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
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 226
-- Name: match_result_user_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.match_result_user_id_seq OWNED BY galop.match_result.team_id;


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
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 213
-- Name: TABLE registration_info; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.registration_info IS '报名信息表';


--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN registration_info.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_info.id IS '主键';


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN registration_info.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_info.create_time IS '创建时间';


--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN registration_info.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_info.update_time IS '更新时间';


--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN registration_info.match_info_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_info.match_info_id IS '比赛信息 ID';


--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 213
-- Name: COLUMN registration_info.team_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.registration_info.team_id IS '团队 ID';


--
-- TOC entry 3026 (class 0 OID 0)
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
-- TOC entry 3027 (class 0 OID 0)
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
-- TOC entry 3028 (class 0 OID 0)
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
-- TOC entry 3029 (class 0 OID 0)
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
-- TOC entry 3030 (class 0 OID 0)
-- Dependencies: 202
-- Name: TABLE role; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.role IS '角色表';


--
-- TOC entry 3031 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN role.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.role.id IS '主键';


--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN role.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.role.create_time IS '创建时间';


--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN role.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.role.update_time IS '更新时间';


--
-- TOC entry 3034 (class 0 OID 0)
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
-- TOC entry 3035 (class 0 OID 0)
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
    team_profile text DEFAULT '这个团队比较懒，还没有简介哦~'::text,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    team_state character varying(8) DEFAULT '审核'::character varying NOT NULL,
    team_fighting_capacity integer NOT NULL,
    team_id bigint
);


ALTER TABLE galop.team OWNER TO postgres;

--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 211
-- Name: TABLE team; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop.team IS '团队表';


--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.id IS '主键';


--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.create_time IS '创建时间';


--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.update_time IS '更新时间';


--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.team_name; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.team_name IS '团队名';


--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.team_profile; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.team_profile IS '简介';


--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.user_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.user_id IS '用户 ID';


--
-- TOC entry 3043 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.role_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.role_id IS '角色 ID';


--
-- TOC entry 3044 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.team_state; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.team_state IS '我的团队状态';


--
-- TOC entry 3045 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.team_fighting_capacity; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.team_fighting_capacity IS '团队总战斗力';


--
-- TOC entry 3046 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN team.team_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop.team.team_id IS '所属团队 ID';


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
-- TOC entry 3047 (class 0 OID 0)
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
-- TOC entry 3048 (class 0 OID 0)
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
-- TOC entry 3049 (class 0 OID 0)
-- Dependencies: 221
-- Name: team_user_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.team_user_id_seq OWNED BY galop.team.user_id;


--
-- TOC entry 205 (class 1259 OID 16613)
-- Name: user; Type: TABLE; Schema: galop; Owner: postgres
--

CREATE TABLE galop."user" (
    id bigint NOT NULL,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    user_number character varying(12) NOT NULL,
    user_name character varying(24) NOT NULL,
    user_password character varying(172) DEFAULT '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om'::character varying NOT NULL,
    user_email character varying(320),
    user_portrait character varying(2083) DEFAULT 'https://localhost:8080/avatar/avatar.png'::character varying NOT NULL,
    role_id bigint NOT NULL,
    user_fighting_capacity integer DEFAULT 0 NOT NULL
);


ALTER TABLE galop."user" OWNER TO postgres;

--
-- TOC entry 3050 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE "user"; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TABLE galop."user" IS '用户表';


--
-- TOC entry 3051 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "user".id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".id IS '主键';


--
-- TOC entry 3052 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "user".create_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".create_time IS '创建时间';


--
-- TOC entry 3053 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "user".update_time; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".update_time IS '更新时间';


--
-- TOC entry 3054 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "user".user_number; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".user_number IS '账号（学号/工号）';


--
-- TOC entry 3055 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "user".user_name; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".user_name IS '姓名';


--
-- TOC entry 3056 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "user".user_password; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".user_password IS '密码';


--
-- TOC entry 3057 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "user".user_email; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".user_email IS '邮箱';


--
-- TOC entry 3058 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "user".role_id; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".role_id IS '角色 ID';


--
-- TOC entry 3059 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "user".user_fighting_capacity; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON COLUMN galop."user".user_fighting_capacity IS '战斗力';


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
-- TOC entry 3060 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.user_id_seq OWNED BY galop."user".id;


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
-- TOC entry 3061 (class 0 OID 0)
-- Dependencies: 218
-- Name: user_role_id_seq; Type: SEQUENCE OWNED BY; Schema: galop; Owner: postgres
--

ALTER SEQUENCE galop.user_role_id_seq OWNED BY galop."user".role_id;


--
-- TOC entry 2787 (class 2604 OID 16684)
-- Name: announcement id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.announcement ALTER COLUMN id SET DEFAULT nextval('galop.announcement_id_seq'::regclass);


--
-- TOC entry 2764 (class 2604 OID 16644)
-- Name: fighting_capacity id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity ALTER COLUMN id SET DEFAULT nextval('galop.fighting_capacity_id_seq'::regclass);


--
-- TOC entry 2767 (class 2604 OID 16716)
-- Name: fighting_capacity match_info_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.fighting_capacity ALTER COLUMN match_info_id SET DEFAULT nextval('galop.fighting_capacity_match_info_id_seq'::regclass);


--
-- TOC entry 2768 (class 2604 OID 16723)
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
-- Name: match_result team_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result ALTER COLUMN team_id SET DEFAULT nextval('galop.match_result_user_id_seq'::regclass);


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
-- TOC entry 2769 (class 2604 OID 16654)
-- Name: team id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team ALTER COLUMN id SET DEFAULT nextval('galop.team_id_seq'::regclass);


--
-- TOC entry 2772 (class 2604 OID 16743)
-- Name: team user_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team ALTER COLUMN user_id SET DEFAULT nextval('galop.team_user_id_seq'::regclass);


--
-- TOC entry 2773 (class 2604 OID 16753)
-- Name: team role_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.team ALTER COLUMN role_id SET DEFAULT nextval('galop.team_role_id_seq'::regclass);


--
-- TOC entry 2754 (class 2604 OID 16616)
-- Name: user id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."user" ALTER COLUMN id SET DEFAULT nextval('galop.user_id_seq'::regclass);


--
-- TOC entry 2758 (class 2604 OID 16697)
-- Name: user role_id; Type: DEFAULT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."user" ALTER COLUMN role_id SET DEFAULT nextval('galop.user_role_id_seq'::regclass);


--
-- TOC entry 2966 (class 0 OID 16681)
-- Dependencies: 217
-- Data for Name: announcement; Type: TABLE DATA; Schema: galop; Owner: postgres
--

INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (1, '2020-05-02 09:59:45.713997', '2020-05-02 09:59:45.713997', '内蒙古工业大学训练赛3月第1场开始报名啦', '{"blocks":[{"key":"f79uh","text":"比赛类型","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1r9uj","text":"训练赛","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"7u1pm","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cc81f","text":"比赛团队规模","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"6m26a","text":"2人","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"32rdr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5qi5s","text":"第一所得奖励","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fm5gp","text":"100分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"26evh","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5mrqr","text":"递减梯度","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cg2l9","text":"10分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1pr9n","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1jrr3","text":"比赛时间","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"c7kt1","text":"2020-03-07 08:00:00 - 2020-03-07 12:00:00","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{"nodeAttributes":{}}},{"key":"e9cfr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"49nfp","text":"请大家踊跃报名哦~","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9j6i6","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fmuu5","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{}}');
INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (2, '2020-05-02 09:59:58.334888', '2020-05-02 09:59:58.334888', '内蒙古工业大学训练赛3月第2场开始报名啦', '{"blocks":[{"key":"f79uh","text":"比赛类型","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1r9uj","text":"训练赛","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"7u1pm","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cc81f","text":"比赛团队规模","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"6m26a","text":"2人","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"32rdr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5qi5s","text":"第一所得奖励","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fm5gp","text":"100分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"26evh","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5mrqr","text":"递减梯度","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cg2l9","text":"10分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1pr9n","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1jrr3","text":"比赛时间","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"c7kt1","text":"2020-03-14 08:00:00 - 2020-03-14 12:00:00","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{"nodeAttributes":{}}},{"key":"e9cfr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"49nfp","text":"请大家踊跃报名哦~","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9j6i6","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fmuu5","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{}}');
INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (3, '2020-05-02 10:00:15.023691', '2020-05-02 10:00:15.023691', '内蒙古工业大学训练赛3月第3场开始报名啦', '{"blocks":[{"key":"f79uh","text":"比赛类型","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1r9uj","text":"训练赛","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"7u1pm","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cc81f","text":"比赛团队规模","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"6m26a","text":"2人","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"32rdr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5qi5s","text":"第一所得奖励","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fm5gp","text":"100分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"26evh","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5mrqr","text":"递减梯度","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cg2l9","text":"10分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1pr9n","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1jrr3","text":"比赛时间","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"c7kt1","text":"2020-03-21 08:00:00 - 2020-03-21 12:00:00","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{"nodeAttributes":{}}},{"key":"e9cfr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"49nfp","text":"请大家踊跃报名哦~","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9j6i6","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fmuu5","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{}}');
INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (4, '2020-05-02 10:00:28.997331', '2020-05-02 10:00:28.997331', '内蒙古工业大学训练赛3月第4场开始报名啦', '{"blocks":[{"key":"f79uh","text":"比赛类型","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1r9uj","text":"训练赛","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"7u1pm","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cc81f","text":"比赛团队规模","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"6m26a","text":"2人","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"32rdr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5qi5s","text":"第一所得奖励","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fm5gp","text":"100分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"26evh","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5mrqr","text":"递减梯度","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cg2l9","text":"10分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1pr9n","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1jrr3","text":"比赛时间","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"c7kt1","text":"2020-03-28 08:00:00 - 2020-03-28 12:00:00","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{"nodeAttributes":{}}},{"key":"e9cfr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"49nfp","text":"请大家踊跃报名哦~","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9j6i6","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fmuu5","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{}}');
INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (5, '2020-05-02 10:00:50.275528', '2020-05-02 10:00:50.275528', '内蒙古工业大学训练赛4月第1场开始报名啦', '{"blocks":[{"key":"f79uh","text":"比赛类型","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1r9uj","text":"训练赛","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"7u1pm","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cc81f","text":"比赛团队规模","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"6m26a","text":"2人","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"32rdr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5qi5s","text":"第一所得奖励","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fm5gp","text":"100分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"26evh","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5mrqr","text":"递减梯度","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cg2l9","text":"10分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1pr9n","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1jrr3","text":"比赛时间","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"c7kt1","text":"2020-03-28 08:00:00 - 2020-03-28 12:00:00","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{"nodeAttributes":{}}},{"key":"e9cfr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"49nfp","text":"请大家踊跃报名哦~","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9j6i6","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fmuu5","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{}}');
INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (6, '2020-05-02 10:02:03.661298', '2020-05-02 10:02:03.661298', '内蒙古工业大学正式赛2020年第1场开始报名啦', '{"blocks":[{"key":"f79uh","text":"比赛类型","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1r9uj","text":"正式赛","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"7u1pm","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cc81f","text":"比赛团队规模","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"6m26a","text":"3人","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"32rdr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5qi5s","text":"第一所得奖励","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fm5gp","text":"200分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"26evh","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5mrqr","text":"递减梯度","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cg2l9","text":"20分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1pr9n","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1jrr3","text":"比赛时间","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"c7kt1","text":"2020-04-25 08:00:00 - 2020-04-25 12:00:00","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{"nodeAttributes":{}}},{"key":"e9cfr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"49nfp","text":"请大家踊跃报名哦~","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9j6i6","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fmuu5","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{}}');
INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (7, '2020-05-02 10:06:23.065853', '2020-05-02 10:06:23.065853', '内蒙古工业大学正式赛2020年第2场开始报名啦', '{"blocks":[{"key":"f79uh","text":"比赛类型","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1r9uj","text":"正式赛","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"7u1pm","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cc81f","text":"比赛团队规模","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"6m26a","text":"3人","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"32rdr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5qi5s","text":"第一所得奖励","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fm5gp","text":"200分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"26evh","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"5mrqr","text":"递减梯度","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"cg2l9","text":"20分","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1pr9n","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"1jrr3","text":"比赛时间","type":"header-one","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"c7kt1","text":"2020-06-06 08:00:00 - 2020-06-06 12:00:00","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{"nodeAttributes":{}}},{"key":"e9cfr","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"49nfp","text":"请大家踊跃报名哦~","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9j6i6","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"fmuu5","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{}}');
INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (8, '2020-05-02 10:31:14.866158', '2020-05-02 10:31:14.866158', '内蒙古工业大学训练赛4月第1场比赛结果公布啦', '{"blocks":[{"key":"3gt4r","text":"点击下载比赛结果","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":8,"style":"COLOR-000000"}],"entityRanges":[{"offset":0,"length":8,"key":0}],"data":{}},{"key":"711lj","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9eijd","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{"0":{"type":"LINK","mutability":"MUTABLE","data":{"href":"https://localhost:8080/file/内蒙古工业大学训练赛4月第1场bbaae589823e4410acebb4042a464ac0.xlsx","target":"_blank"}}}}');
INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (9, '2020-05-02 10:43:55.498209', '2020-05-02 10:43:55.498209', '内蒙古工业大学训练赛3月第1场比赛结果公布啦', '{"blocks":[{"key":"3gt4r","text":"点击下载比赛结果","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":8,"style":"COLOR-000000"}],"entityRanges":[{"offset":0,"length":8,"key":0}],"data":{}},{"key":"711lj","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9eijd","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{"0":{"type":"LINK","mutability":"MUTABLE","data":{"href":"https://localhost:8080/file/内蒙古工业大学训练赛3月第1场eb6cab27ab454a12919814c8609b375a.xlsx","target":"_blank"}}}}');
INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (10, '2020-05-02 10:48:00.670953', '2020-05-02 10:48:00.670953', '内蒙古工业大学训练赛3月第2场比赛结果公布啦', '{"blocks":[{"key":"3gt4r","text":"点击下载比赛结果","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":8,"style":"COLOR-000000"}],"entityRanges":[{"offset":0,"length":8,"key":0}],"data":{}},{"key":"711lj","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9eijd","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{"0":{"type":"LINK","mutability":"MUTABLE","data":{"href":"https://localhost:8080/file/内蒙古工业大学训练赛3月第2场1b6f5724e1ff457ba7ecd7a87cc7df71.xlsx","target":"_blank"}}}}');
INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (11, '2020-05-02 10:49:37.984324', '2020-05-02 10:49:37.984324', '内蒙古工业大学训练赛3月第3场比赛结果公布啦', '{"blocks":[{"key":"3gt4r","text":"点击下载比赛结果","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":8,"style":"COLOR-000000"}],"entityRanges":[{"offset":0,"length":8,"key":0}],"data":{}},{"key":"711lj","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9eijd","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{"0":{"type":"LINK","mutability":"MUTABLE","data":{"href":"https://localhost:8080/file/内蒙古工业大学训练赛3月第3场4a41693d360643339a0b37e5edb37402.xlsx","target":"_blank"}}}}');
INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (12, '2020-05-02 10:50:48.105725', '2020-05-02 10:50:48.105725', '内蒙古工业大学训练赛3月第4场比赛结果公布啦', '{"blocks":[{"key":"3gt4r","text":"点击下载比赛结果","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":8,"style":"COLOR-000000"}],"entityRanges":[{"offset":0,"length":8,"key":0}],"data":{}},{"key":"711lj","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9eijd","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{"0":{"type":"LINK","mutability":"MUTABLE","data":{"href":"https://localhost:8080/file/内蒙古工业大学训练赛3月第4场ba48fce5df264e4b814fe102d5d4cc56.xlsx","target":"_blank"}}}}');
INSERT INTO galop.announcement (id, create_time, update_time, announcement_title, announcement_content) VALUES (13, '2020-05-02 10:57:23.382147', '2020-05-02 10:57:23.382147', '内蒙古工业大学正式赛2020年第1场比赛结果公布啦', '{"blocks":[{"key":"3gt4r","text":"点击下载比赛结果","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":8,"style":"COLOR-000000"}],"entityRanges":[{"offset":0,"length":8,"key":0}],"data":{}},{"key":"711lj","text":"","type":"unstyled","depth":0,"inlineStyleRanges":[],"entityRanges":[],"data":{}},{"key":"9eijd","text":"本条公告发自机器人管理员 😇 ~","type":"unstyled","depth":0,"inlineStyleRanges":[{"offset":0,"length":13,"style":"COLOR-07A9FE"},{"offset":15,"length":1,"style":"COLOR-07A9FE"}],"entityRanges":[],"data":{}}],"entityMap":{"0":{"type":"LINK","mutability":"MUTABLE","data":{"href":"https://localhost:8080/file/内蒙古工业大学正式赛2020年第1场d4aae91e42fe4eec8aff7f8df767e975.xlsx","target":"_blank"}}}}');


--
-- TOC entry 2958 (class 0 OID 16641)
-- Dependencies: 209
-- Data for Name: fighting_capacity; Type: TABLE DATA; Schema: galop; Owner: postgres
--

INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (4, '2020-04-11 12:00:00.09', '2020-05-02 10:33:35.700367', 5, 70, 5);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (5, '2020-04-11 12:00:00.09', '2020-05-02 10:33:35.700367', 5, 60, 8);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (6, '2020-04-11 12:00:00.09', '2020-05-02 10:33:35.700367', 5, 50, 9);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (1, '2020-04-11 12:00:00.09', '2020-05-02 10:33:35.700367', 5, 100, 2);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (2, '2020-04-11 12:00:00.09', '2020-05-02 10:33:35.700367', 5, 90, 3);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (3, '2020-04-11 12:00:00.09', '2020-05-02 10:33:35.700367', 5, 80, 4);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (12, '2020-03-07 12:00:00.09', '2020-05-02 10:48:28.845329', 1, 80, 5);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (8, '2020-03-07 12:00:00.09', '2020-05-02 10:48:28.845329', 1, 100, 7);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (9, '2020-03-07 12:00:00.09', '2020-05-02 10:48:28.845329', 1, 90, 2);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (10, '2020-03-07 12:00:00.09', '2020-05-02 10:48:28.845329', 1, 90, 3);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (11, '2020-03-07 12:00:00.09', '2020-05-02 10:48:28.845329', 1, 80, 4);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (7, '2020-03-07 12:00:00.09', '2020-05-02 10:48:28.845329', 1, 100, 6);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (13, '2020-03-14 12:00:00.09', '2020-05-02 10:48:51.861342', 2, 100, 3);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (14, '2020-03-14 12:00:00.09', '2020-05-02 10:48:51.861342', 2, 100, 2);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (15, '2020-03-14 12:00:00.09', '2020-05-02 10:48:51.861342', 2, 90, 4);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (16, '2020-03-14 12:00:00.09', '2020-05-02 10:48:51.861342', 2, 90, 5);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (18, '2020-03-21 12:00:00.09', '2020-05-02 10:50:07.150324', 3, 100, 3);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (19, '2020-03-21 12:00:00.09', '2020-05-02 10:50:07.150324', 3, 90, 4);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (20, '2020-03-21 12:00:00.09', '2020-05-02 10:50:07.150324', 3, 90, 5);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (21, '2020-03-21 12:00:00.09', '2020-05-02 10:50:07.150324', 3, 80, 6);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (22, '2020-03-21 12:00:00.09', '2020-05-02 10:50:07.150324', 3, 80, 7);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (17, '2020-03-21 12:00:00.09', '2020-05-02 10:50:07.150324', 3, 100, 2);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (24, '2020-03-28 12:00:00.09', '2020-05-02 10:51:06.999145', 4, 100, 3);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (23, '2020-03-28 12:00:00.09', '2020-05-02 10:51:06.999145', 4, 100, 2);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (28, '2020-04-25 12:00:00.09', '2020-05-02 10:57:41.702312', 6, 180, 4);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (29, '2020-04-25 12:00:00.09', '2020-05-02 10:57:41.702312', 6, 180, 3);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (30, '2020-04-25 12:00:00.09', '2020-05-02 10:57:41.702312', 6, 180, 7);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (25, '2020-04-25 12:00:00.09', '2020-05-02 10:57:41.702312', 6, 200, 2);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (26, '2020-04-25 12:00:00.09', '2020-05-02 10:57:41.702312', 6, 200, 10);
INSERT INTO galop.fighting_capacity (id, create_time, update_time, match_info_id, reward, user_id) VALUES (27, '2020-04-25 12:00:00.09', '2020-05-02 10:57:41.702312', 6, 200, 11);


--
-- TOC entry 2956 (class 0 OID 16623)
-- Dependencies: 207
-- Data for Name: match_info; Type: TABLE DATA; Schema: galop; Owner: postgres
--

INSERT INTO galop.match_info (id, create_time, update_time, match_name, team_size, champion_award, decrement_parameter, start_time, end_time, match_type) VALUES (2, '2020-03-07 09:59:58.334', '2020-05-02 10:05:10.389664', '内蒙古工业大学训练赛3月第2场', 2, 100, 10, '2020-03-14 08:00:00.09', '2020-03-14 12:00:00.09', '训练赛');
INSERT INTO galop.match_info (id, create_time, update_time, match_name, team_size, champion_award, decrement_parameter, start_time, end_time, match_type) VALUES (3, '2020-03-14 10:00:15.023', '2020-05-02 10:05:10.389664', '内蒙古工业大学训练赛3月第3场', 2, 100, 10, '2020-03-21 08:00:00.09', '2020-03-21 12:00:00.09', '训练赛');
INSERT INTO galop.match_info (id, create_time, update_time, match_name, team_size, champion_award, decrement_parameter, start_time, end_time, match_type) VALUES (4, '2020-03-21 10:00:28.997', '2020-05-02 10:05:10.389664', '内蒙古工业大学训练赛3月第4场', 2, 100, 10, '2020-03-28 08:00:00.09', '2020-03-28 12:00:00.09', '训练赛');
INSERT INTO galop.match_info (id, create_time, update_time, match_name, team_size, champion_award, decrement_parameter, start_time, end_time, match_type) VALUES (1, '2020-03-02 09:59:45.713', '2020-05-02 10:05:10.389664', '内蒙古工业大学训练赛3月第1场', 2, 100, 10, '2020-03-07 08:00:00.09', '2020-03-07 12:00:00.09', '训练赛');
INSERT INTO galop.match_info (id, create_time, update_time, match_name, team_size, champion_award, decrement_parameter, start_time, end_time, match_type) VALUES (6, '2020-04-11 10:02:03.661', '2020-05-02 10:05:10.389664', '内蒙古工业大学正式赛2020年第1场', 3, 200, 20, '2020-04-25 08:00:00.09', '2020-04-25 12:00:00.09', '正式赛');
INSERT INTO galop.match_info (id, create_time, update_time, match_name, team_size, champion_award, decrement_parameter, start_time, end_time, match_type) VALUES (7, '2020-04-25 10:06:23.065', '2020-05-02 10:06:44.263125', '内蒙古工业大学正式赛2020年第2场', 3, 200, 20, '2020-06-06 08:00:00.38', '2020-06-06 12:00:00.906', '正式赛');
INSERT INTO galop.match_info (id, create_time, update_time, match_name, team_size, champion_award, decrement_parameter, start_time, end_time, match_type) VALUES (5, '2020-03-28 10:00:50.275', '2020-05-02 10:16:17.808241', '内蒙古工业大学训练赛4月第1场', 1, 100, 10, '2020-04-11 08:00:00.09', '2020-04-11 12:00:00.09', '训练赛');


--
-- TOC entry 2964 (class 0 OID 16671)
-- Dependencies: 215
-- Data for Name: match_result; Type: TABLE DATA; Schema: galop; Owner: postgres
--

INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (1, '2020-04-11 12:00:00.09', '2020-05-02 10:33:45.938679', 5, 2, 1);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (2, '2020-04-11 12:00:00.09', '2020-05-02 10:33:45.938679', 5, 3, 2);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (3, '2020-04-11 12:00:00.09', '2020-05-02 10:33:45.938679', 5, 5, 3);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (4, '2020-04-11 12:00:00.09', '2020-05-02 10:33:45.938679', 5, 8, 4);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (5, '2020-04-11 12:00:00.09', '2020-05-02 10:33:45.938679', 5, 11, 5);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (6, '2020-04-11 12:00:00.09', '2020-05-02 10:33:45.938679', 5, 12, 6);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (8, '2020-03-07 12:00:00.09', '2020-05-02 10:48:34.340988', 1, 1, 2);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (7, '2020-03-07 12:00:00.09', '2020-05-02 10:48:34.340988', 1, 9, 1);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (9, '2020-03-07 12:00:00.09', '2020-05-02 10:48:34.340988', 1, 6, 3);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (10, '2020-03-14 12:00:00.09', '2020-05-02 10:48:56.602536', 2, 1, 1);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (11, '2020-03-14 12:00:00.09', '2020-05-02 10:48:56.602536', 2, 6, 2);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (13, '2020-03-21 12:00:00.09', '2020-05-02 10:50:13.171364', 3, 6, 2);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (14, '2020-03-21 12:00:00.09', '2020-05-02 10:50:13.171364', 3, 9, 3);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (12, '2020-03-21 12:00:00.09', '2020-05-02 10:50:13.171364', 3, 1, 1);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (15, '2020-03-28 12:00:00.09', '2020-05-02 10:51:12.271311', 4, 1, 1);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (16, '2020-04-25 12:00:00.09', '2020-05-02 10:57:47.834833', 6, 13, 1);
INSERT INTO galop.match_result (id, create_time, update_time, match_info_id, team_id, ranking) VALUES (17, '2020-04-25 12:00:00.09', '2020-05-02 10:57:47.834833', 6, 16, 2);


--
-- TOC entry 2962 (class 0 OID 16661)
-- Dependencies: 213
-- Data for Name: registration_info; Type: TABLE DATA; Schema: galop; Owner: postgres
--

INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (1, '2020-05-02 10:18:05.026417', '2020-05-02 10:29:11.229306', 5, 2, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (2, '2020-05-02 10:19:30.764246', '2020-05-02 10:29:15.643088', 5, 3, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (3, '2020-05-02 10:21:12.764203', '2020-05-02 10:29:18.965506', 5, 5, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (4, '2020-05-02 10:22:43.964783', '2020-05-02 10:29:20.725112', 5, 8, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (5, '2020-05-02 10:26:36.887968', '2020-05-02 10:29:22.075125', 5, 11, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (6, '2020-05-02 10:28:48.205168', '2020-05-02 10:29:23.822524', 5, 12, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (14, '2020-05-02 10:41:05.862389', '2020-05-02 10:42:40.538003', 1, 9, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (7, '2020-05-02 10:36:22.846813', '2020-05-02 10:42:41.702533', 1, 1, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (11, '2020-05-02 10:38:01.687088', '2020-05-02 10:42:45.994461', 1, 6, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (12, '2020-05-02 10:38:04.49042', '2020-05-02 10:46:33.754624', 2, 6, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (8, '2020-05-02 10:36:25.257016', '2020-05-02 10:46:36.474658', 2, 1, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (15, '2020-05-02 10:41:07.721981', '2020-05-02 10:47:08.71913', 3, 9, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (13, '2020-05-02 10:38:06.504206', '2020-05-02 10:47:09.398059', 3, 6, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (9, '2020-05-02 10:36:27.863665', '2020-05-02 10:47:10.193695', 3, 1, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (10, '2020-05-02 10:36:30.851836', '2020-05-02 10:47:11.399458', 4, 1, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (17, '2020-05-02 10:56:26.435865', '2020-05-02 10:56:40.808411', 6, 13, '通过');
INSERT INTO galop.registration_info (id, create_time, update_time, match_info_id, team_id, registration_status) VALUES (16, '2020-05-02 10:55:57.212552', '2020-05-02 10:56:41.76171', 6, 16, '通过');


--
-- TOC entry 2951 (class 0 OID 16581)
-- Dependencies: 202
-- Data for Name: role; Type: TABLE DATA; Schema: galop; Owner: postgres
--

INSERT INTO galop.role (id, create_time, update_time, role_name) VALUES (1, '2020-04-18 22:52:41.612336', '2020-04-18 22:52:41.612336', '老师');
INSERT INTO galop.role (id, create_time, update_time, role_name) VALUES (2, '2020-04-18 22:53:29.408748', '2020-04-18 22:53:29.408748', '学生');
INSERT INTO galop.role (id, create_time, update_time, role_name) VALUES (3, '2020-04-18 22:53:29.408748', '2020-04-18 22:53:29.408748', '队长');


--
-- TOC entry 2960 (class 0 OID 16651)
-- Dependencies: 211
-- Data for Name: team; Type: TABLE DATA; Schema: galop; Owner: postgres
--

INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (8, '2020-05-02 10:22:29.496887', '2020-05-02 10:49:37.984324', '张辰海就喜欢一个人战斗', '这个团队比较懒，还没有简介哦~', 5, 3, '通过', 330, 8);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (13, '2020-05-02 10:53:29.522067', '2020-05-02 10:57:23.382147', '2020年第1场正式赛1队', '必胜', 2, 3, '通过', 1090, 13);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (14, '2020-05-02 10:53:46.439783', '2020-05-02 10:57:23.382147', '2020年第1场正式赛1队', '必胜', 10, 2, '通过', 1090, 13);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (15, '2020-05-02 10:54:01.58617', '2020-05-02 10:57:23.382147', '2020年第1场正式赛1队', '必胜', 11, 2, '通过', 1090, 13);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (6, '2020-05-02 10:21:01.461572', '2020-05-02 10:57:23.382147', '乌合之众', '我想组队参加比赛', 4, 3, '通过', 850, 6);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (11, '2020-05-02 10:26:24.91539', '2020-05-02 10:31:14.866158', '张默喜欢emoji✨😊❤😍😒', '✨😊❤😍😒', 8, 3, '通过', 60, 11);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (12, '2020-05-02 10:28:40.293445', '2020-05-02 10:31:14.866158', '我只参加单人训练赛', '别想加入我的团队', 9, 3, '通过', 50, 12);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (7, '2020-05-02 10:21:43.2054', '2020-05-02 10:57:23.382147', '乌合之众', '我想组队参加比赛', 5, 2, '通过', 850, 6);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (2, '2020-05-02 10:11:06.946257', '2020-05-02 10:57:23.382147', '张天赐的一人团队', '这个团队不收人', 2, 3, '通过', 690, 2);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (5, '2020-05-02 10:20:26.282973', '2020-05-02 10:57:23.382147', '张观博的一人团队', '我只想一个人参加比赛', 4, 3, '通过', 520, 5);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (1, '2020-05-02 10:10:34.212441', '2020-05-02 10:57:23.382147', '张天赐的团队', '团队不要战斗力低于5的渣渣', 2, 3, '通过', 1350, 1);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (4, '2020-05-02 10:19:36.931286', '2020-05-02 10:57:23.382147', '张天赐的团队', '团队不要战斗力低于5的渣渣', 3, 2, '通过', 1350, 1);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (3, '2020-05-02 10:19:11.283546', '2020-05-02 10:57:23.382147', '张诗涵的一人团队', '这个团队只有我一人', 3, 3, '通过', 660, 3);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (10, '2020-05-02 10:25:39.569831', '2020-05-02 10:57:23.382147', '张兴飞从不单打独斗', '张兴飞从不单打独斗', 7, 2, '通过', 540, 9);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (9, '2020-05-02 10:25:17.55857', '2020-05-02 10:57:23.382147', '张兴飞从不单打独斗', '张兴飞从不单打独斗', 6, 3, '通过', 540, 9);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (16, '2020-05-02 10:54:52.83871', '2020-05-02 10:57:23.382147', '2020年第1场正式赛2队', '我们比1队强', 4, 3, '通过', 880, 16);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (17, '2020-05-02 10:55:07.725414', '2020-05-02 10:57:23.382147', '2020年第1场正式赛2队', '我们比1队强', 3, 2, '通过', 880, 16);
INSERT INTO galop.team (id, create_time, update_time, team_name, team_profile, user_id, role_id, team_state, team_fighting_capacity, team_id) VALUES (18, '2020-05-02 10:55:23.290733', '2020-05-02 10:57:23.382147', '2020年第1场正式赛2队', '我们比1队强', 7, 2, '通过', 880, 16);


--
-- TOC entry 2954 (class 0 OID 16613)
-- Dependencies: 205
-- Data for Name: user; Type: TABLE DATA; Schema: galop; Owner: postgres
--

INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (12, '2020-05-02 09:57:40.900249', '2020-05-02 09:57:40.900249', '20060000363', '安兴亚', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/avatar.png', 1, 0);
INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (1, '2020-05-02 09:45:37.964706', '2020-05-02 09:58:43.507462', '000000', '机器人管理员', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/avatar.png', 1, 0);
INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (6, '2020-05-02 09:57:40.858373', '2020-05-02 10:49:37.984324', '201620205005', '张兴飞', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/avatar.png', 2, 180);
INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (2, '2020-05-02 09:57:40.819767', '2020-05-02 10:57:23.382147', '201620205001', '张天赐', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/201620205001.jpg', 2, 690);
INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (10, '2020-05-02 09:57:40.884441', '2020-05-02 10:57:23.382147', '201620205009', '张紫琪', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/avatar.png', 2, 200);
INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (8, '2020-05-02 09:57:40.871844', '2020-05-02 10:31:14.866158', '201620205007', '张默', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/201620205007.png', 2, 60);
INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (9, '2020-05-02 09:57:40.878048', '2020-05-02 10:31:14.866158', '201620205008', '张韵涵', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/avatar.png', 2, 50);
INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (11, '2020-05-02 09:57:40.891861', '2020-05-02 10:57:23.382147', '201620205010', '张一鸣', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/avatar.png', 2, 200);
INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (4, '2020-05-02 09:57:40.841722', '2020-05-02 10:57:23.382147', '201620205003', '张观博', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/201620205003.png', 2, 520);
INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (3, '2020-05-02 09:57:40.833218', '2020-05-02 10:57:23.382147', '201620205002', '张诗涵', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/201620205002.png', 2, 660);
INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (7, '2020-05-02 09:57:40.864565', '2020-05-02 10:57:23.382147', '201620205006', '张雨荨', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/avatar.png', 2, 360);
INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (5, '2020-05-02 09:57:40.850767', '2020-05-02 11:04:44.253608', '201620205004', '张辰海', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/201620205004.png', 2, 330);
INSERT INTO galop."user" (id, create_time, update_time, user_number, user_name, user_password, user_email, user_portrait, role_id, user_fighting_capacity) VALUES (13, '2020-05-02 11:07:34.178503', '2020-05-02 11:07:34.178503', '201620205011', '张贝馨', '$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om', '1018729292@qq.com', 'https://localhost:8080/avatar/avatar.png', 2, 0);


--
-- TOC entry 3062 (class 0 OID 0)
-- Dependencies: 216
-- Name: announcement_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.announcement_id_seq', 13, true);


--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 208
-- Name: fighting_capacity_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.fighting_capacity_id_seq', 30, true);


--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 219
-- Name: fighting_capacity_match_info_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.fighting_capacity_match_info_id_seq', 1, false);


--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 220
-- Name: fighting_capacity_user_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.fighting_capacity_user_id_seq', 1, false);


--
-- TOC entry 3066 (class 0 OID 0)
-- Dependencies: 206
-- Name: match_info_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.match_info_id_seq', 7, true);


--
-- TOC entry 3067 (class 0 OID 0)
-- Dependencies: 214
-- Name: match_result_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.match_result_id_seq', 17, true);


--
-- TOC entry 3068 (class 0 OID 0)
-- Dependencies: 225
-- Name: match_result_match_info_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.match_result_match_info_id_seq', 1, false);


--
-- TOC entry 3069 (class 0 OID 0)
-- Dependencies: 226
-- Name: match_result_user_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.match_result_user_id_seq', 1, false);


--
-- TOC entry 3070 (class 0 OID 0)
-- Dependencies: 212
-- Name: registration_info_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.registration_info_id_seq', 17, true);


--
-- TOC entry 3071 (class 0 OID 0)
-- Dependencies: 223
-- Name: registration_info_match_info_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.registration_info_match_info_id_seq', 1, false);


--
-- TOC entry 3072 (class 0 OID 0)
-- Dependencies: 224
-- Name: registration_info_team_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.registration_info_team_id_seq', 1, false);


--
-- TOC entry 3073 (class 0 OID 0)
-- Dependencies: 203
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.role_id_seq', 3, true);


--
-- TOC entry 3074 (class 0 OID 0)
-- Dependencies: 210
-- Name: team_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.team_id_seq', 18, true);


--
-- TOC entry 3075 (class 0 OID 0)
-- Dependencies: 222
-- Name: team_role_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.team_role_id_seq', 1, false);


--
-- TOC entry 3076 (class 0 OID 0)
-- Dependencies: 221
-- Name: team_user_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.team_user_id_seq', 1, false);


--
-- TOC entry 3077 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: galop; Owner: postgres
--

SELECT pg_catalog.setval('galop.user_id_seq', 14, true);


--
-- TOC entry 3078 (class 0 OID 0)
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
-- Name: user user_pk; Type: CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."user"
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

CREATE UNIQUE INDEX user_user_number_uindex ON galop."user" USING btree (user_number);


--
-- TOC entry 2824 (class 2620 OID 16779)
-- Name: announcement update_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER update_time BEFORE UPDATE ON galop.announcement FOR EACH ROW EXECUTE FUNCTION galop.update_time();


--
-- TOC entry 3079 (class 0 OID 0)
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
-- TOC entry 3080 (class 0 OID 0)
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
-- TOC entry 3081 (class 0 OID 0)
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
-- TOC entry 3082 (class 0 OID 0)
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
-- TOC entry 3083 (class 0 OID 0)
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
-- TOC entry 3084 (class 0 OID 0)
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
-- TOC entry 3085 (class 0 OID 0)
-- Dependencies: 2821
-- Name: TRIGGER update_time ON team; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER update_time ON galop.team IS '更新时间';


--
-- TOC entry 2818 (class 2620 OID 16772)
-- Name: user update_time; Type: TRIGGER; Schema: galop; Owner: postgres
--

CREATE TRIGGER update_time BEFORE UPDATE ON galop."user" FOR EACH ROW EXECUTE FUNCTION galop.update_time();


--
-- TOC entry 3086 (class 0 OID 0)
-- Dependencies: 2818
-- Name: TRIGGER update_time ON "user"; Type: COMMENT; Schema: galop; Owner: postgres
--

COMMENT ON TRIGGER update_time ON galop."user" IS '更新时间';


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
    ADD CONSTRAINT fighting_capacity_user_id_fk FOREIGN KEY (user_id) REFERENCES galop."user"(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2815 (class 2606 OID 16829)
-- Name: match_result match_result_match_info_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result
    ADD CONSTRAINT match_result_match_info_id_fk FOREIGN KEY (match_info_id) REFERENCES galop.match_info(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2816 (class 2606 OID 16841)
-- Name: match_result match_result_team_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop.match_result
    ADD CONSTRAINT match_result_team_id_fk FOREIGN KEY (team_id) REFERENCES galop.team(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


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
    ADD CONSTRAINT team_user_id_fk FOREIGN KEY (user_id) REFERENCES galop."user"(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2808 (class 2606 OID 16707)
-- Name: user user_role_id_fk; Type: FK CONSTRAINT; Schema: galop; Owner: postgres
--

ALTER TABLE ONLY galop."user"
    ADD CONSTRAINT user_role_id_fk FOREIGN KEY (role_id) REFERENCES galop.role(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


-- Completed on 2020-05-02 11:34:07

--
-- PostgreSQL database dump complete
--

