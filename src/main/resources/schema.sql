drop schema library;
create schema library;
use library;

create table author(
    id int AUTO_INCREMENT not null,
    name varchar(50) not null,
    email varchar(50) not null,
    author_book int not null,
    primary key(id),
    foreign key(author_book) references book(isbn)
);

create table book(
    isbn varchar(17) not null,
    title varchar(255) not null,
    category varchar(50) not null,
    quantity int not null,
    author_id int not null,
    primary key(isbn),
    foreign key(author_id) references author(id)
);

CREATE TABLE issue(
    issue_id INT AUTO_INCREMENT not null,
    issue_date varchar(255) not null,
    return_date varchar(255) not null,
    issue_student varchar(255) not null,
    issue_book varchar(17) not null,
    primary key(issue_id)
    foreign key(issue_student) references student(usn),
    foreign key(issue_book) references book(isbn)
);

CREATE TABLE student(
	usn varchar(11) not null,
    name varchar(255) not null,
    primary key(usn)
);