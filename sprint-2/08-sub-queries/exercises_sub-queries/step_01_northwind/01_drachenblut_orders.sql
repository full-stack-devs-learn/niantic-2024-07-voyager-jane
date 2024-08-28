-- 1. Create a report to show
-- all orders that were placed by Drachenblut Delikatessen.

-- Columns to include:
-- -------------------
-- order_id
-- order_date
-- shipped_date


-- Order the results by shipped date, newest to oldest.
-- (6 rows)

-- Using only a sub-query (no INNER JOIN)


USE northwind;

SELECT o.order_id
	, o.order_date
	, o.shipped_date
FROM orders AS o
WHERE customer_id = (
						SELECT customer_id
                        FROM customers
                        WHERE company_name = 'Drachenblut Delikatessen'
					)
ORDER BY shipped_date DESC;

