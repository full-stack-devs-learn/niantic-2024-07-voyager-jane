-- 7. Select the price of the most expensive product

-- (1 row)

USE northwind;

SELECT sales_price
FROM customer_orders
ORDER BY sales_price DESC
LIMIT 1;

