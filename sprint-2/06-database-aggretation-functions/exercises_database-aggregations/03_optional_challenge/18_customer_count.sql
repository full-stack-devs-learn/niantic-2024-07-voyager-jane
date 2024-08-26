-- 18. Count the number of customers in each country
-- include the country and total_customer_count
-- (use customers table)

-- (21 rows)

USE northwind;

SELECT country,
	COUNT(DISTINCT customer_id) AS total_customer_count
FROM customer_orders
GROUP BY country;



