USE northwind;

-- Return all countries and cities where we 
-- have customers

-- Expected: 69 Rows

SELECT DISTINCT city
	, country
FROM customers;