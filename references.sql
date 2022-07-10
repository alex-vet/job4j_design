create table publisher (
    id serial primary key,
    name varchar(255),
);

create table isbn (
    id serial primary key,
    name varchar(255),
);

create table genre (
    id serial primary key,
    name varchar(255),
);

--many-to-many
create table genres_books (
    id serial primary key,
	genre_id integer references genre(id),
	book_id integer references book(id)
);

--many-to-one by publisher_id column
--one-to-one by isbn_id column
create table book (
    id serial primary key,
    name varchar(255),
	page_count integer,
	read boolean,
	publisher_id integer references publisher(id),
	isbn_id integer references isbn(id) unique
);