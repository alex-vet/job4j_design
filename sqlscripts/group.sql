create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);


INSERT INTO devices(name, price)
	VALUES ('Monitor 214S', 5976.00),
		   ('Keyboard 134K', 569.40),
	 	   ('Printer 458P', 22492.90),
		   ('Videocard 785V', 36776.10),
	       ('Mouse 12M', 526.50);

INSERT INTO people(name)
	VALUES ('Anton'),
		   ('Andry'),
		   ('Alex'),
	 	   ('Max'),
	 	   ('Ingrid');

INSERT INTO devices_people(device_id, people_id)
	VALUES (2, 1), (3, 2), (3, 3), (4, 2), (5, 5), (1, 4), (1, 3);

SELECT ROUND(AVG(price)::numeric,2) AS avg_price
  FROM devices;

SELECT p.name AS people,ROUND(AVG(price)::numeric,2) AS avg_price
  FROM devices_people AS dp
  	   INNER JOIN devices AS d ON dp.device_id=d.id
	   INNER JOIN people AS p ON dp.people_id=p.id
 GROUP BY p.name;

SELECT p.name AS people,ROUND(AVG(price)::numeric,2) AS avg_price
  FROM devices_people AS dp
  	   INNER JOIN devices AS d ON dp.device_id=d.id
	   INNER JOIN people AS p ON dp.people_id=p.id
 GROUP BY p.name
HAVING ROUND(AVG(price)::numeric,2) > 5000;