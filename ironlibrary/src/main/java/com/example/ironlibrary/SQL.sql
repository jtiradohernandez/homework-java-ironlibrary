drop schema library;
create schema library;
use library;

create table book(
id int AUTO_INCREMENT not null,
isbn varchar(64) not null,
title varchar(255) not null,
category enum('HORROR',
    'SCIENCE',
    'ROMANCE',
    'FICTION',
    'FANTASY',
    'ADVENTURE',
    'BIOGRAPHY',
    'MISTERY',
    'OTHERS'),
quantity int not null,
primary key(id)
);

CREATE TABLE library.author(
	author_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    author_book VARCHAR(255),
    PRIMARY KEY (author_id),
    FOREIGN KEY (author_book) REFERENCES library.book(isbn)
    );



