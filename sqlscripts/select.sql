-- 4.2. Написать скрипт для выполнения следующих запросов:
-- 1) Извлечение данных, у которых имя name содержит подстроку fish
-- 2) Извлечение данных, у которых сред. продолжительность жизни находится в диапазоне 10 000 и 21 000
-- 3) Извлечение данных, у которых дата открытия не известна (null)
-- 4) Извлечение данных видов, у которых дата открытия раньше 1950 года

SELECT *
  FROM fauna
 WHERE name like '%fish%';

 SELECT *
  FROM fauna
 WHERE avg_age between 10000 AND 20000;

SELECT *
  FROM fauna
 WHERE discovery_date IS NULL;

SELECT *
  FROM fauna
 WHERE date_part('year',discovery_date) < 1950;