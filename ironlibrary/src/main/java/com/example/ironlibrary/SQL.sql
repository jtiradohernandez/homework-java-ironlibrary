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
