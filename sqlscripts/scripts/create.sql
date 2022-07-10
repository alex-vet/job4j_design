CREATE TABLE role(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE users(
	id serial primary key,
	name varchar(255),
	role_id integer REFERENCES role(id)
);

CREATE TABLE rules(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE role_rules(
	id serial primary key,
	role_id integer REFERENCES role(id),
	rules_id integer REFERENCES rules(id)
);

CREATE TABLE category(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE state(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE item(
	id serial primary key,
	name varchar(255),
	users_id integer REFERENCES users(id),
	category_id integer REFERENCES category(id),
	state_id integer REFERENCES state(id)
);

CREATE TABLE comments(
	id serial primary key,
	name varchar(255),
	item_id integer REFERENCES item(id)
);

CREATE TABLE attachs(
	id serial primary key,
	name varchar(255),
	item_id integer REFERENCES item(id)
);