DROP TABLE IF EXISTS public.user_role_map;
DROP TABLE IF EXISTS public.teams;
DROP TABLE IF EXISTS public.roles;
DROP TABLE IF EXISTS public.teams;
DROP TABLE IF EXISTS public.leagues;
DROP TABLE IF EXISTS public.tokens;
DROP TABLE IF EXISTS public.sport;
DROP TABLE IF EXISTS public.app_user;
DROP SEQUENCE IF EXISTS public.app_user_seq;
DROP SEQUENCE IF EXISTS public.user_role_seq;
DROP SEQUENCE IF EXISTS public.team_seq;
DROP SEQUENCE IF EXISTS public.league_seq;
DROP SEQUENCE IF EXISTS public.token_seq;
DROP SEQUENCE IF EXISTS public.sport_seq;
DROP SEQUENCE IF EXISTS public.app_user_org_seq;


CREATE SEQUENCE public.token_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.user_role_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;


CREATE SEQUENCE public.app_user_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.app_user_org_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.league_seq
  INCREMENT BY 50
  MINVALUE 1
  MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.sport_seq
  INCREMENT BY 50
  MINVALUE 1
  MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.team_seq
  INCREMENT BY 50
  MINVALUE 1
  MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

CREATE TABLE public.app_user (
	 id int8 NOT NULL,
	 orgid   varchar(500) UNIQUE,
	 rolename varchar(255) NOT NULL,
	 password varchar(255) NOT NULL,
	 username varchar(255)  NOT NULL,
	 orgname varchar(500)  NULL,
	 email  varchar(255) NOT NULL,
	 address varchar(700) NOT NULL,
	 phone  varchar(12) NOT NULL,
	 CONSTRAINT app_user_pkey PRIMARY KEY (id),
	 CONSTRAINT  unique_rec UNIQUE(orgid,email,username)

);



CREATE TABLE public.user_role_map (
	id int8 NOT NULL,
	role_id int8 NULL,
	user_id int8 NULL,
	CONSTRAINT user_role_map_pkey PRIMARY KEY (id),
	CONSTRAINT fktjg3yvrylq5a4xbhjvjwhi2a5 FOREIGN KEY (user_id) REFERENCES app_user(id)
);



CREATE TABLE public.roles (
	role_id int8 NOT NULL,
	description varchar(255) NULL,
	rolename varchar(255) NULL,
	CONSTRAINT role_pkey PRIMARY KEY (role_id)
);

CREATE TABLE public.sport (
  id int8 NOT NULL,
  orgid   varchar(500) NOT NULL,
  user_id int8 NOT NULL,
  name varchar(255) NULL,
  description varchar(255) NULL,
  CONSTRAINT sport_Pkey PRIMARY KEY (id),
  CONSTRAINT orgkey_sport FOREIGN KEY (orgid) REFERENCES app_user(orgid),
  CONSTRAINT user_id_forkey FOREIGN KEY (user_id) REFERENCES app_user(id),
  CONSTRAINT sportname UNIQUE  (name)
);

CREATE TABLE public.leagues (
  league_id int8 NOT NULL,
  orgid   varchar(500) NOT NULL,
  user_id int8 NOT NULL,
  league_name varchar(255) NULL,
  description varchar(255) NULL,
  sport_id int8 NULL,
  age_min int8 NULL,
  age_max int8 NULL,
  coed boolean NULL,
  team_min int8 NULL,
  team_max int8 NULL,
  league_schedule varchar(255) NULL,
  league_rules varchar(255) NULL,
  CONSTRAINT league_pkey PRIMARY KEY (league_id),
  CONSTRAINT orgkey_leagues FOREIGN KEY (orgid) REFERENCES app_user(orgid),
  CONSTRAINT sport_league_uniqukey UNIQUE  (league_id, sport_id),
  CONSTRAINT user_id_forkey FOREIGN KEY (user_id) REFERENCES app_user(id),
  CONSTRAINT sport_id_forkey FOREIGN KEY (sport_id) REFERENCES sport(id)
);


CREATE TABLE public.tokens (
	id int8 NOT NULL,
	user_id int8 NOT NULL,
	created_time timestamp NOT NULL,
	token varchar(255) UNIQUE,
	username varchar(255) NOT NULL,
	CONSTRAINT tokens_pkey PRIMARY KEY (id),
	CONSTRAINT user_id_forkey FOREIGN KEY (user_id) REFERENCES app_user(id)

);



CREATE TABLE public.teams (
  team_id int8 NOT NULL,
  team_name   varchar(500) NOT NULL,
  team_manager varchar(255) NULL,
  description varchar(255) NULL,
  league_id int8 NOT NULL,
  CONSTRAINT team_Pkey PRIMARY KEY (team_id),
  CONSTRAINT league_id FOREIGN KEY (league_id) REFERENCES leagues(league_id)
);