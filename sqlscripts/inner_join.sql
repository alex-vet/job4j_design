create table publisher (
    id serial primary key,
    name varchar(255)
);

create table book (
    id serial primary key,
    name varchar(255),
	publisher_id integer references publisher(id)
);

insert into publisher(name) values ('АСТ');
insert into publisher(name) values ('Algonquin Books');
insert into publisher(name) values ('St. Martin Press');

insert into book(name, publisher_id) values ('Book Lovers', 1);
insert into book(name, publisher_id) values ('The Temple House Vanishing', 2);
insert into book(name, publisher_id) values ('Perpetual West', 3);
insert into book(name) values ('Small Odysseys');
insert into book(name, publisher_id) values ('Our Time Is Now', 3);

SELECT b.name AS Название, p.name AS Издатель
  FROM book AS b
 INNER JOIN publisher AS p
 	   ON b.publisher_id = p.id;

SELECT b.name AS Название, p.name AS Издатель
  FROM book AS b
 INNER JOIN publisher AS p
 	   ON b.publisher_id = p.id
 WHERE b.id > 1;

SELECT b.name AS Название, p.name AS Издатель
  FROM book AS b
 INNER JOIN publisher AS p
 	   ON b.publisher_id = p.id
 WHERE p.id = 3;