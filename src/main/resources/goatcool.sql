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



INSERT INTO userBase VALUES('12ab#.', 'mentor', 'mentor', 'm', 'm');
INSERT INTO userBase VALUES('34cd#.', 'student', 'student', 's', 's');
INSERT INTO userBase VALUES('56ab#.', 'mentor', 'Berta', 'berta', 'berta');
INSERT INTO userBase VALUES('34ef#.', 'student', 'Andi', 'andi', 'andi');
INSERT INTO userBase VALUES('12gb#.', 'mentor', 'Attila', 'attila', 'attila');
INSERT INTO userBase VALUES('34ch#.', 'mentor', 'Tamy', 'tamy', 'tamy');

INSERT INTO page VALUES('56ef#.', 'Kecske Text', true);
INSERT INTO text_page VALUES('56ef#.', 'Kecske!!!!!');
INSERT INTO page VALUES('78zh#.', 'Kecske Assignment', false);
INSERT INTO assignment_page VALUES('78zh#.', 'Kecske????', 5);
INSERT INTO page VALUES('78uh#.', 'New Assignment', true);
INSERT INTO assignment_page VALUES('78uh#.', 'Tomcat?', 5);
INSERT INTO page VALUES('78jh#.', 'Database Assignment', true);
INSERT INTO assignment_page VALUES('78jh#.', 'SQL?', 5);


INSERT INTO newsfeed VALUES ('99gh#.', 'Test', 'Lorem ipsum dolor sit amet...', '2019/04/07 21:45:00', '34ch#.');
INSERT INTO newsfeed VALUES ('89gh#.', 'Test2', 'Lorem ipsum dolor sit amet...', '2019/04/02 09:01:00', '34ch#.');
INSERT INTO newsfeed VALUES ('79gh#.', 'Test3', 'Lorem ipsum dolor sit amet...', '2019/04/05 08:55:00', '34ch#.');


