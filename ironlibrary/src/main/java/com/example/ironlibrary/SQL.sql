CREATE SCHEMA library;

CREATE TABLE library.author(
	author_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    author_book VARCHAR(255),
    PRIMARY KEY (author_id),
    FOREIGN KEY (author_book) REFERENCES library.book(isbn)
    );