SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET default_tablespace = '';

SET default_with_oids = false;

DROP TABLE IF EXISTS solutionmap CASCADE;
DROP TABLE IF EXISTS page CASCADE;
DROP TABLE IF EXISTS solution CASCADE;
DROP TABLE IF EXISTS student CASCADE;
DROP TABLE IF EXISTS mentor CASCADE;
DROP TABLE IF EXISTS page CASCADE;
DROP TABLE IF EXISTS assignment_page CASCADE;
DROP TABLE IF EXISTS text_page CASCADE;
DROP TABLE IF EXISTS userBase CASCADE;

CREATE TABLE userBase(
	user_id varchar(6) NOT NULL,
	user_role varchar(10) NOT NULL,
	email varchar(254) UNIQUE NOT NULL,
	user_name varchar(15) NOT NULL,
	password varchar(15),
	PRIMARY KEY(user_id)
);

CREATE TABLE solution(
	solution_id varchar(6) NOT NULL,
	user_id varchar(6) NOT NULL,
	title varchar(100) NOT NULL,
	answer varchar(300) NOT NULL,
	grade integer,
	submission_date timestamp NOT NULL,
	PRIMARY KEY (solution_id),
	FOREIGN KEY (user_id) REFERENCES userBase(user_id)
);

CREATE TABLE page(
	page_id varchar(6) NOT NULL,
	title varchar(100) NOT NULL,
	isPublished boolean,
	PRIMARY KEY(page_id)
);

CREATE TABLE text_page(
	page_id varchar(6) NOT NULL,
	content text NOT NULL,
	PRIMARY KEY(page_id),
	FOREIGN KEY (page_id) REFERENCES page(page_id)
);

CREATE TABLE assignment_page(
	page_id varchar(6) NOT NULL,
    question text NOT NULL,
    max_score integer NOT NULL,
	PRIMARY KEY(page_id),
    FOREIGN KEY (page_id) REFERENCES page(page_id)
);

CREATE TABLE solutionmap(
	user_id varchar(6),
	solution_id varchar(6),
	FOREIGN KEY (user_id) REFERENCES userBase(user_id),
	FOREIGN KEY (solution_id) REFERENCES solution(solution_id)
);




