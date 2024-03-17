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

CREATE TABLE library.author(
	author_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    author_book VARCHAR(255),
    PRIMARY KEY (author_id),
    FOREIGN KEY (author_book) REFERENCES library.book(isbn)
);

CREATE TABLE issue (
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
