-- 11. Least Popular film report
-- return the name of the 20 least popular films
-- title and number of times each film was rented

-- which tables will you need to join?

--
-- (20 rows)

USE sakila;

SELECT f.title AS film_title
	, COUNT(r.rental_id) AS rent_count
FROM film AS f
INNER JOIN inventory AS i ON f.film_id = i.film_id
INNER JOIN rental AS r ON i.inventory_id = r.inventory_id
GROUP BY f.title
ORDER BY rent_count
LIMIT 20;


