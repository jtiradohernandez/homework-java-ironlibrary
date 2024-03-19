drop schema library;
create schema library;
use library;

create table book(
isbn varchar(64) not null,
title varchar(255) not null,
category enum('HORROR',
    'SCIENCE',
    'ROMANCE',
    'FICTION',
    'FANTASY',
    'ADVENTURE',
    'BIOGRAPHY',
    'MYSTERY',
    'OTHERS'),
quantity int not null,
primary key(isbn)
);

CREATE TABLE Issue (
    issueId INT AUTO_INCREMENT NOT NULL,
    issueDate VARCHAR(255) NOT NULL,
    returnDate VARCHAR(255) NOT NULL,
    PRIMARY KEY(issueId)
);

CREATE TABLE student (
	usn VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (usn)
    );