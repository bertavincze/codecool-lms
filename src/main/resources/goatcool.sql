SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET default_tablespace = '';

SET default_with_oids = false;

SET TIME ZONE 'GMT';

DROP TABLE IF EXISTS solutionmap CASCADE;
DROP TABLE IF EXISTS page CASCADE;
DROP TABLE IF EXISTS solution CASCADE;
DROP TABLE IF EXISTS student CASCADE;
DROP TABLE IF EXISTS mentor CASCADE;
DROP TABLE IF EXISTS page CASCADE;
DROP TABLE IF EXISTS assignment_page CASCADE;
DROP TABLE IF EXISTS text_page CASCADE;
DROP TABLE IF EXISTS userBase CASCADE;
DROP TABLE IF EXISTS attendance CASCADE;
DROP TABLE IF EXISTS newsfeed CASCADE;



CREATE TABLE userBase(
	user_id varchar(6) NOT NULL,
	user_role varchar(10) NOT NULL,
	user_name varchar(15) NOT NULL,
	email varchar(254) UNIQUE NOT NULL,
	password text,
	image_id text NOT NULL,
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
	page_id varchar(6),
	FOREIGN KEY (user_id) REFERENCES userBase(user_id),
	FOREIGN KEY (solution_id) REFERENCES solution(solution_id),
	FOREIGN KEY (page_id) REFERENCES assignment_page(page_id)
);

CREATE TABLE attendance(
    attendance_id varchar(6) NOT NULL,
    user_id varchar(6) NOT NULL,
    attended_day date,
    is_present boolean,
    PRIMARY KEY (attendance_id),
    FOREIGN KEY (user_id) REFERENCES userBase(user_id)
);


CREATE TABLE newsfeed(
    news_id varchar(6) NOT NULL,
    title varchar(100) NOT NULL,
    content varchar(300) NOT NULL,
    date TIMESTAMP WITH TIME ZONE NOT NULL,
    user_id varchar(6),
    PRIMARY KEY (news_id),
    FOREIGN KEY (user_id) REFERENCES userBase(user_id)
);





INSERT INTO userBase VALUES('12ab#.', 'mentor', 'mentor', 'mentor@gmail.com', '1000:f0df76015ad1889b478111dba08f8549:246f41d4d9e10271946e2e3696c9d58865fd0cf80081cffcf773e676da930f73c62732a2be3064cb2bd2aa33e7ac6879a1a5e48bfdca80cfad1307485346590b', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('34cd#.', 'student', 'student', 'student@gmail.com', '1000:367c3d3e0946e061c8bd0251477c86fb:f116261b8558e552ebe0b5ec6d8387ca27eea79650666fe94187bbf7e0154f62be93e570b44e3424d0cdaba384ccf2084e848e7d63c64c31f8d9a47dc71d0160', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('56ab#.', 'mentor', 'Berta', 'berta@gmail.com', '1000:5a0cb039b75b41d5da28c9617174c94e:cb3e0fe403c38d03799c8c6727655a49d9a175af48c55feb7d2fc7195637c6afb288a6205b2d012fe150f3aa6efa6cb35d1a8b6dc523c6ef58a9bfcbff3bd07d', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('34ef#.', 'student', 'Andi', 'andi@gmail.com', '1000:dba70971922bea97006ef7eee5b5e17d:27f1e63c9603856d048ff0b07a330c7bd23bc7a6731da1e27744640f4238e3f779b9652105c738eae30e3edc3bfda939954b18073b45e42b477b4188e5dc1fbc', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12gb#.', 'mentor', 'Attila', 'attila@gmail.com', '1000:890e281ac33fd93865e1abe4dd4f37f2:dc7370de09fb13b5563332e9c6b41d27fcc7d26da938d21702a3aadd81f2de9c5c488fbe21137ffa8b64044697da5c5ab884ca5d155a9362d46145a9e88d8f04', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('34ch#.', 'mentor', 'Tamy', 'tamy@gmail.com', '1000:3fc8a6758c2b7342ef6f6e7d3808082a:12048bdbbe2f31458d497f6a4e7f7f504ea9a13c08b8cc2116f37c988613c0a5d040ba8aa3ca6523aa5c80060ee725ab2943b7152ba5b46e756816e5abcf7293', 'resources/pics/profile.jpg');

--Text pages // need to correct text diaplay
INSERT INTO page VALUES('56ef#.', 'Sessions', true);
INSERT INTO text_page VALUES('56ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('51ec#.', 'Filters', true);
INSERT INTO text_page VALUES('51ec#.', 'Filters allow to pass or block a requests based on some criteria. They decide which request to block by inspecting the "request" object. Requests containing a JSESSIONID which has an associated attribute, flag, object, etc. which signifies that the user (which to the JSESSIONID belongs to) has already logged in are allowed to pass, other requests are blocked (e.g. redirected to the login page).');
INSERT INTO page VALUES('59ea#.', 'Handling XML with Java?', false);
INSERT INTO text_page VALUES('59ea#.', 'When you were dealing with CSV you had to write custom code to read and write CSV files. This is what we call serialization (writing objects to a file in a certain format) and deserialization (reading a file in a certain format into objects). You have to do something similar when dealing with XML, but it is a little more complex, and you have to get a grasp of the DOM concept.');

--Assignment pages
INSERT INTO page VALUES('78zh#.', 'What is the output1 ?', false);
INSERT INTO assignment_page VALUES('78zh#.', 'Kecske????', 10);
INSERT INTO page VALUES('78uh#.', 'New Assignment', true);
INSERT INTO assignment_page VALUES('78uh#.', 'Tomcat?', 10);
INSERT INTO page VALUES('78jh#.', 'Database Assignment', true);
INSERT INTO assignment_page VALUES('78jh#.', 'SQL?', 10);



--INSERT INTO newsfeed VALUES ('99gh#.', 'Still no coffee', 'The situation is getting worse and worse, students are falling asleep at their laptop...', '2019/04/07 21:45:00', '34ch#.');
INSERT INTO newsfeed VALUES ('89gh#.', 'Total Panic', 'After students raided the newly working coffe machine, we regret to announce, that we are out of coffe beans for the foreseeable future', '2019/04/05 09:01:00', '34ch#.');
INSERT INTO newsfeed VALUES ('79gh#.', 'COFFEE AGAIN', 'After eight weeks without a coffe machine, it is finally working again', '2019/04/02 08:55:00', '34ch#.');




