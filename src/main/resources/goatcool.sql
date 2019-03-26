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
DROP TABLE IF EXISTS curriculum CASCADE;
DROP TABLE IF EXISTS assignment CASCADE;
DROP TABLE IF EXISTS userBase CASCADE;

CREATE TABLE userBase(
	userID varchar(6) NOT NULL,
	role varchar(10) NOT NULL,
	email varchar(254) UNIQUE NOT NULL,
	name varchar(15),
	password varchar(15),
	PRIMARY KEY(userID)
);

CREATE TABLE solution(
	solutionID varchar(6),
	userID varchar(6),
	title varchar(100) NOT NULL,
	answer varchar(300),
	question varchar(300),
	time date,
	PRIMARY KEY (solutionID),
	FOREIGN KEY (userID) REFERENCES userBase(userID)
);

CREATE TABLE page(
	pageID varchar(6),
	title varchar(100) NOT NULL,
	isPublished boolean,
	PRIMARY KEY(pageID)
);

CREATE TABLE curriculum(
	curriculumID varchar(6),
	pageID varchar(6),
	PRIMARY KEY (curriculumID),
	FOREIGN KEY (pageID) REFERENCES page(pageID)
);

CREATE TABLE assignment(
	assignmentID varchar(6),
	pageID varchar(6),
	PRIMARY KEY (assignmentID),
	FOREIGN KEY (pageID) REFERENCES page(pageID)
);

CREATE TABLE solutionmap(
	userID varchar(6),
	solutionID varchar(6),
	FOREIGN KEY (userID) REFERENCES userBase(userID),
	FOREIGN KEY (solutionID) REFERENCES solution(solutionID)
);


