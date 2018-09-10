-- Drop table
DROP TABLE IF EXISTS public.app_user_roles;
DROP TABLE IF EXISTS public.user_role;
DROP TABLE IF EXISTS public.app_user;
DROP TABLE IF EXISTS public.roles;
DROP SEQUENCE IF EXISTS public.app_user_seq;
DROP SEQUENCE IF EXISTS public.app_user_roles_seq;


CREATE SEQUENCE public.app_user_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE public.app_user_roles_seq
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	CACHE 1
	NO CYCLE;

CREATE TABLE public.app_user (
	user_id int8 NOT NULL,
	"password" varchar(255) NOT NULL,
	 username varchar(255) NOT NULL,
	CONSTRAINT app_user_pkey PRIMARY KEY (username)
);


CREATE TABLE public.user_role (
	role_id int8 NOT NULL,
	description varchar(255) NULL,
	rolename varchar(255) NULL,
	CONSTRAINT user_role_pkey PRIMARY KEY (role_id)
);


CREATE TABLE public.roles (
	role_id int8 NOT NULL,
	description varchar(255) NULL,
	rolename varchar(255) NULL,
	CONSTRAINT role_pkey PRIMARY KEY (role_id)
);

CREATE TABLE public.app_user_roles (
	app_user_user_id int8 NOT NULL,
	roles_role_id int8 NOT NULL
);
