create table type (
    id serial primary key,
    name varchar(255)
);

create table product (
    id serial primary key,
    name varchar(255),
	type_id integer references type(id),
	expired_date date,
	price numeric(9,2)
);

insert into type(name) values ('СЫР');
insert into type(name) values ('МОЛОКО');
insert into type(name) values ('ХЛЕБ');

insert into product(name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2022-08-01', 358.41);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла', 1, '2022-12-29', 2576.95);
insert into product(name, type_id, expired_date, price) values ('Мороженное SLASTI', 2, '2022-07-29', 157.82);
insert into product(name, type_id, expired_date, price) values ('Молоко Три коровы', 2, '2022-07-20', 80.14);
insert into product(name, type_id, expired_date, price) values ('Мороженное На набережной', 2, '2022-07-30', 356.14);
insert into product(name, type_id, expired_date, price) values ('Хлеб домашний', 3, '2022-07-26', 45.20);

SELECT p.name AS Продукт, t.name AS "Тип продукта", expired_date as "Срок годности", price as Цена
  FROM product AS p
 	   INNER JOIN type AS t
 	   ON p.type_id = t.id
 WHERE t.name = 'СЫР'

SELECT p.name AS Продукт, t.name AS "Тип продукта", expired_date as "Срок годности", price as Цена
  FROM product AS p
 	   INNER JOIN type AS t
 	   ON p.type_id = t.id
 WHERE p.name like '%ороженное%'

SELECT p.name AS Продукт, t.name AS "Тип продукта", expired_date as "Срок годности", price as Цена
  FROM product AS p
 	   INNER JOIN type AS t
 	   ON p.type_id = t.id
 WHERE expired_date < current_date

SELECT p.name AS Продукт, t.name AS "Тип продукта", expired_date as "Срок годности", price as Цена
  FROM product AS p
 	   INNER JOIN type AS t
 	   ON p.type_id = t.id
 WHERE p.price = (SELECT MAX(price) from product)

SELECT t.name AS "Тип продукта", COUNT(*) as Количество
  FROM product AS p
 	   INNER JOIN type AS t
 	   ON p.type_id = t.id
 GROUP BY t.name

SELECT p.name AS Продукт, t.name AS "Тип продукта", expired_date as "Срок годности", price as Цена
  FROM product AS p
 	   INNER JOIN type AS t
 	   ON p.type_id = t.id
 WHERE t.name IN ('СЫР', 'МОЛОКО')

SELECT t.name AS "Тип продукта", COUNT(*) as Количество
  FROM product AS p
 	   INNER JOIN type AS t
 	   ON p.type_id = t.id
 GROUP BY t.name
HAVING COUNT(*) < 10

SELECT p.name AS Продукт, t.name AS "Тип продукта", expired_date as "Срок годности", price as Цена
  FROM product AS p
 	   INNER JOIN type AS t
 	   ON p.type_id = t.id