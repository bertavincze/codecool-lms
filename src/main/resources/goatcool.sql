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




--Users
INSERT INTO userBase VALUES('56ab#.', 'mentor', 'Berta', 'berta@gmail.com', '1000:5a0cb039b75b41d5da28c9617174c94e:cb3e0fe403c38d03799c8c6727655a49d9a175af48c55feb7d2fc7195637c6afb288a6205b2d012fe150f3aa6efa6cb35d1a8b6dc523c6ef58a9bfcbff3bd07d', 'resources/pics/lenny.png');
INSERT INTO userBase VALUES('34ef#.', 'student', 'Andi', 'andi@gmail.com', '1000:dba70971922bea97006ef7eee5b5e17d:27f1e63c9603856d048ff0b07a330c7bd23bc7a6731da1e27744640f4238e3f779b9652105c738eae30e3edc3bfda939954b18073b45e42b477b4188e5dc1fbc', 'resources/pics/lenny.png');
INSERT INTO userBase VALUES('12gb#.', 'mentor', 'Attila', 'attila@gmail.com', '1000:890e281ac33fd93865e1abe4dd4f37f2:dc7370de09fb13b5563332e9c6b41d27fcc7d26da938d21702a3aadd81f2de9c5c488fbe21137ffa8b64044697da5c5ab884ca5d155a9362d46145a9e88d8f04', 'resources/pics/cage5.jpg');
INSERT INTO userBase VALUES('34ch#.', 'mentor', 'Tamy', 'tamy@gmail.com', '1000:3fc8a6758c2b7342ef6f6e7d3808082a:12048bdbbe2f31458d497f6a4e7f7f504ea9a13c08b8cc2116f37c988613c0a5d040ba8aa3ca6523aa5c80060ee725ab2943b7152ba5b46e756816e5abcf7293', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12df#.', 'student', 'Jules', 'jules@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12ef#.', 'student', 'Julie', 'julie@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12de#.', 'student', 'Charlie', 'charlie@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('22de#.', 'student', 'Brandon', 'brandon@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('13de#.', 'student', 'Marie-Claire', 'marieclaire@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12dg#.', 'student', 'Jade', 'jade@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12dt#.', 'student', 'Jess', 'jess@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12dz#.', 'student', 'MacAndCheese', 'mac@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12te#.', 'student', 'Roger', 'roger@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12ge#.', 'student', 'Kate', 'kate@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12he#.', 'student', 'Bálint', 'balint@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12ke#.', 'student', 'Rupert', 'rupert@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12ee#.', 'student', 'Dean', 'dean@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12ce#.', 'student', 'Rory', 'rory@gmail.com', 'Test1', 'resources/pics/profile.jpg');
INSERT INTO userBase VALUES('12we#.', 'student', 'Lorelei', 'lorelei@gmail.com', 'Test1', 'resources/pics/profile.jpg');

--Text pages // need to correct text display
INSERT INTO page VALUES('56ef#.', 'Sessions', true);
INSERT INTO text_page VALUES('56ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('51ec#.', 'Filters', true);
INSERT INTO text_page VALUES('51ec#.', 'Filters allow to pass or block a requests based on some criteria. They decide which request to block by inspecting the "request" object. Requests containing a JSESSIONID which has an associated attribute, flag, object, etc. which signifies that the user (which to the JSESSIONID belongs to) has already logged in are allowed to pass, other requests are blocked (e.g. redirected to the login page).');
INSERT INTO page VALUES('59ea#.', 'Handling XML with Java?', false);
INSERT INTO text_page VALUES('59ea#.', 'When you were dealing with CSV you had to write custom code to read and write CSV files. This is what we call serialization (writing objects to a file in a certain format) and deserialization (reading a file in a certain format into objects). You have to do something similar when dealing with XML, but it is a little more complex, and you have to get a grasp of the DOM concept.');
INSERT INTO page VALUES('66ef#.', 'Yet another language to master: Java', true);
INSERT INTO text_page VALUES('66ef#.', 'Java programming language was originally developed by Sun Microsystems which was initiated by James Gosling and released in 1995 as core component of Sun Microsystems'' Java platform (Java 1.0 [J2SE]). The latest stable release of the Java Standard Edition is Java SE 9. With the advancement of Java and its widespread popularity, multiple configurations were built to suit various types of platforms. For example: J2EE for Enterprise Applications, J2ME for Mobile Applications. <br> <br> The new J2 versions were renamed as Java SE, Java EE, and Java ME respectively. Java is guaranteed to be Write Once, Run Anywhere. <br><ul><li>Object Oriented − In Java, everything is an Object. Java can be easily extended since it is based on the Object model.</li><li>Simple − Java is designed to be easy to learn. If you understand the basic concept of OOP Java, it would be easy to master.</li><li>Secure − With Java''s secure feature it enables to develop virus-free, tamper-free systems. Authentication techniques are based on public-key encryption.</li></ul>');
INSERT INTO page VALUES('76ef#.', 'Java vs. Python', true);
INSERT INTO text_page VALUES('76ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('86ef#.', 'Interpreted vs. compiled', true);
INSERT INTO text_page VALUES('86ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('96ef#.', 'for vs foreach', false);
INSERT INTO text_page VALUES('96ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('16ef#.', 'Method signature', true);
INSERT INTO text_page VALUES('16ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('26ef#.', 'Compile-time vs runtime', true);
INSERT INTO text_page VALUES('26ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('36ef#.', 'Static vs dynamic typing', true);
INSERT INTO text_page VALUES('36ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('46ef#.', 'The new, the instanceof and the cast', false);
INSERT INTO text_page VALUES('46ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('51ef#.', 'The Waterfall project management method', true);
INSERT INTO text_page VALUES('51ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('52ef#.', 'How to compile and run Java', true);
INSERT INTO text_page VALUES('52ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('53ef#.', 'Enums', true);
INSERT INTO text_page VALUES('53ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('54ef#.', 'Arrays', false);
INSERT INTO text_page VALUES('54ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('55ef#.', 'Javadoc', true);
INSERT INTO text_page VALUES('55ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('57ef#.', 'Exception handling', true);
INSERT INTO text_page VALUES('57ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('58ef#.', 'toString()?', true);
INSERT INTO text_page VALUES('58ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('59ef#.', 'Modifiers - pppp, static, final', false);
INSERT INTO text_page VALUES('59ef#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('56af#.', 'Choosing the Right Method Signature', true);
INSERT INTO text_page VALUES('56af#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('56bf#.', 'Getters-Setters', true);
INSERT INTO text_page VALUES('56bf#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('56cf#.', 'Values and references, == vs equals()', false);
INSERT INTO text_page VALUES('56cf#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('56df#.', 'Pass-by-value or pass-by-reference?', true);
INSERT INTO text_page VALUES('56df#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('56ff#.', 'Heap, Stack and Garbage Collection', true);
INSERT INTO text_page VALUES('56ff#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('56gf#.', 'How to use the Official Java documentation?', false);
INSERT INTO text_page VALUES('56gf#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('56hf#.', 'Java OOP Concepts', true);
INSERT INTO text_page VALUES('56hf#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');
INSERT INTO page VALUES('56zf#.', 'UML: Unified Modelling Language', true);
INSERT INTO text_page VALUES('56zf#.', 'Session is a generic term in the context of computers, programming. Here is a very broad description of it. You have seen and used session during the Green Commitment project. Clients connected the server, sent some information over their socket connection, then terminated the connection. This was basically a session between the client and the server. Right now what we are interested in are HTTP sessions.');


--Assignment pages
INSERT INTO page VALUES('78zh#.', 'What is the output1?', false);
INSERT INTO assignment_page VALUES('78zh#.', 'Kecske????', 10);
INSERT INTO page VALUES('88zh#.', 'Does this method work?', true);
INSERT INTO assignment_page VALUES('88zh#.', 'The following method purports to determine whether its sole argument is an odd number. Does the method work? <br><br>public static boolean isOdd(int i){<br>return i % 2 == 1;}', 3);
INSERT INTO page VALUES('98zh#.', 'What is the output?', true);
INSERT INTO assignment_page VALUES('98zh#.', 'Consider the following word problem:<br> Tom goes to the auto parts store to buy a spark plug that costs $1.10, but all he has in his wallet are two-dollar bills. How much change should he get if he pays for the spark plug with a two-dollar bill? <br><br>Here is a program that attempts to solve the word problem. What does it print? <br><br>public class Change {<br>public static void main(String args[]) {<br>System.out.println(2.00 - 1.10);}<br>}', 10);
INSERT INTO page VALUES('18zh#.', 'It is elementary', false);
INSERT INTO assignment_page VALUES('18zh#.', 'This program involves only addition. What does it print? <br><br>public class Elementary {<br>public static void main(String[] args) {<br>System.out.println(12345 + 5432l);}<br>}', 10);
INSERT INTO page VALUES('28uh#.', 'Multicast', true);
INSERT INTO assignment_page VALUES('28uh#.', 'Casts are used to convert a value from one type to another. This program uses three casts in succession. What does it print?<br><br>public class Multicast {<br>public static void main(String[] args) {<br>System.out.println((int) (char) (byte) -1);}<br>}', 10);
INSERT INTO page VALUES('78jh#.', 'Write a code!', true);
INSERT INTO assignment_page VALUES('78jh#.', 'Now it''s your turn to write some code. On the bright side, you have to write only two lines for this puzzle and two lines for the next. How hard could that be? Provide declarations for the variables x and i such that this is a legal statement:<br>x += i;<br><br>but this is not:<br>x = x + i;', 10);


--Newsfeed
INSERT INTO newsfeed VALUES ('99gh#.', 'Still no coffee', 'The situation is getting worse and worse, students are falling asleep at their laptop...', '2019/04/07 21:45:00', '34ch#.');
INSERT INTO newsfeed VALUES ('89gh#.', 'Total Panic', 'After students raided the newly working coffee machine, we regret to announce, that we are out of coffee beans for the foreseeable future', '2019/04/05 09:01:00', '34ch#.');
INSERT INTO newsfeed VALUES ('79gh#.', 'COFFEE AGAIN', 'After eight weeks without a coffee machine, it is finally working again', '2019/04/02 08:55:00', '34ch#.');




