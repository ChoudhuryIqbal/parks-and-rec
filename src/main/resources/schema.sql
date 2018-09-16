
DROP TABLE IF EXISTS public.user_role_map;
DROP TABLE IF EXISTS public.app_user;
DROP TABLE IF EXISTS public.roles;
DROP TABLE IF EXISTS public.leagues;
DROP SEQUENCE IF EXISTS public.app_user_seq;
DROP SEQUENCE IF EXISTS public.user_role_seq;
DROP SEQUENCE IF EXISTS public.league_seq;


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

CREATE SEQUENCE public.league_seq
  INCREMENT BY 50
  MINVALUE 1
  MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

CREATE TABLE public.app_user (
	id int8 NOT NULL,
	"password" varchar(255) NULL,
	user_id int8 NULL,
	username varchar(255) NULL,
	CONSTRAINT app_user_pkey PRIMARY KEY (id),
	CONSTRAINT uk_qjho89hmbuv32f9a8tbocpr0i UNIQUE (user_id)
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


CREATE TABLE public.leagues (
  league_id int8 NOT NULL,
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
  CONSTRAINT league_pkey PRIMARY KEY (league_id)
);

