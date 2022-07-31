CREATE TABLE car_bodies (
	id serial PRIMARY KEY,
	name varchar(255)
);

CREATE TABLE car_engines (
	id serial PRIMARY KEY,
	name varchar(255)
);

CREATE TABLE car_transmissions (
	id serial PRIMARY KEY,
	name varchar(255)
);

CREATE TABLE cars (
	id serial PRIMARY KEY,
	name varchar(255),
	body_id integer REFERENCES car_bodies(id),
	engine_id integer REFERENCES car_engines(id),
	transmission_id integer REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies (name) VALUES ('Седан');
INSERT INTO car_bodies (name) VALUES ('Хэтчбек');
INSERT INTO car_bodies (name) VALUES ('Пикап');

INSERT INTO car_engines (name) VALUES ('V8 MAD RC');
INSERT INTO car_engines (name) VALUES ('V1 CRT');
INSERT INTO car_engines (name) VALUES ('ДВС 1765СД');
INSERT INTO car_engines (name) VALUES ('ДВС АН759');

INSERT INTO car_transmissions (name) VALUES ('КПП ZF 16S181');
INSERT INTO car_transmissions (name) VALUES ('Maybach Mekydro K-104');
INSERT INTO car_transmissions (name) VALUES ('КПП К-744 ИС');
INSERT INTO car_transmissions (name) VALUES ('КПП К-895 АС');

INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES ('Volkswagen Scirocco', 1, 1, 2);
INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES ('LADA (ВАЗ) Granta', 1, 3, 3);
INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES ('LADA (ВАЗ) 2110', 1, null, 3);
INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES ('LADA (ВАЗ) Vesta', 2, 2, 1);

SELECT c.id, c.name as car_name, b.name as body_name, e.name as engine_name, t.name as transmission_name
  FROM cars as c
  	   LEFT JOIN car_bodies as b
	   ON c.body_id = b.id
	   LEFT JOIN car_engines as e
	   ON c.engine_id = e.id
	   LEFT JOIN car_transmissions as t
	   ON c.transmission_id = t.id;

SELECT b.name as body_name
  FROM cars as c
  	   RIGHT JOIN car_bodies as b
	   ON c.body_id = b.id
 WHERE c.id IS NULL;

 SELECT e.name as engine_name
  FROM cars as c
	   RIGHT JOIN car_engines as e
	   ON c.engine_id = e.id
 WHERE c.id IS NULL;

SELECT t.name as transmission_name
  FROM cars as c
	   RIGHT JOIN car_transmissions as t
	   ON c.transmission_id = t.id
 WHERE c.id IS NULL;
