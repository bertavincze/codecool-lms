SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET default_tablespace = '';

SET default_with_oids = false;

DROP TABLE IF EXISTS solutionmap;
DROP TABLE IF EXISTS page;
DROP TABLE IF EXISTS solution;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS mentor;
DROP TABLE IF EXISTS curtriculum;
DROP TABLE IF EXISTS assignment;
DROP TABLE IF EXISTS userBase;

CREATE TABLE userBase(
	userID varchar(6) NOT NULL,
	email varchar(254) UNIQUE NOT NULL,
	name varchar(15),
	password varchar(15),
	PRIMARY KEY(userID)
);

CREATE TABLE mentor(
	mentorID varchar(6) NOT NULL,
	userID varchar(6) NOT NULL,
	PRIMARY KEY(mentorID),
	FOREIGN KEY (userID) REFERENCES userBase(userID)
);

CREATE TABLE student(
	studentID varchar(6),
	userID varchar(6),
	PRIMARY KEY (studentID),
	FOREIGN KEY (userID) REFERENCES userBase(userID)
);

CREATE TABLE solution(
	solutionID varchar(6),
	studentID varchar(6),
	title varchar(100) NOT NULL,
	answer varchar(300),
	question varchar(300),
	time date,
	PRIMARY KEY (solutionID),
	FOREIGN KEY (studentID) REFERENCES student(studentID)
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
	studentID varchar(6),
	solutionID varchar(6),
	FOREIGN KEY (studentID) REFERENCES student(studentID),
	FOREIGN KEY (solutionID) REFERENCES solution(solutionID)
);

