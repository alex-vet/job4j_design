create table departments (
    id serial primary key,
    name varchar(255)
);

create table employees (
    id serial primary key,
    name varchar(255),
	departments_id integer references departments(id)
);

insert into departments(name) values ('Администрация');
insert into departments(name) values ('Бухгалтерия');
insert into departments(name) values ('Производство');
insert into departments(name) values ('Отдел кадров');

insert into employees(name, departments_id) values ('Директор', 1);
insert into employees(name, departments_id) values ('Заместитель директора', 1);
insert into employees(name, departments_id) values ('Бухгалтер', 2);
insert into employees(name, departments_id) values ('Инженер', 3);
insert into employees(name, departments_id) values ('Бригадир', 3);
insert into employees(name, departments_id) values ('Уборщица', 3);

SELECT d.name AS Департамент, e.name AS "Должность"
  FROM employees AS e
 	   LEFT JOIN departments AS d
 	   ON e.departments_id = d.id;

SELECT d.name AS Департамент, e.name AS "Должность"
  FROM employees AS e
 	   RIGHT JOIN departments AS d
 	   ON e.departments_id = d.id;

SELECT d.name AS Департамент, e.name AS "Должность"
  FROM employees AS e
 	   FULL JOIN departments AS d
 	   ON e.departments_id = d.id;

SELECT d.name AS Департамент, e.name AS "Должность"
  FROM employees AS e
 	   CROSS JOIN departments AS d;

SELECT d.name AS Департамент, e.name AS "Должность"
  FROM departments AS d
 	   LEFT JOIN employees AS e
 	   ON  d.id = e.departments_id
 WHERE e.name IS NULL;


SELECT d.name AS Департамент, e.name AS "Должность"
  FROM employees AS e
 	   LEFT JOIN departments AS d
 	   ON e.departments_id = d.id;

SELECT d.name AS Департамент, e.name AS "Должность"
  FROM departments AS d
 	   RIGHT JOIN employees AS e
 	   ON  d.id = e.departments_id;


create table teens (
    id serial primary key,
    name varchar(255),
	gender varchar(1)
);

insert into teens(name, gender) values ('Анна', 'Ж');
insert into teens(name, gender) values ('Марина', 'Ж');
insert into teens(name, gender) values ('Алексей', 'М');
insert into teens(name, gender) values ('Александр', 'М');
insert into teens(name, gender) values ('Евгений', 'М');

SELECT t1.name, t2.name
  FROM teens AS t1
  	   CROSS JOIN teens AS t2
 WHERE t1.gender <> t2.gender;