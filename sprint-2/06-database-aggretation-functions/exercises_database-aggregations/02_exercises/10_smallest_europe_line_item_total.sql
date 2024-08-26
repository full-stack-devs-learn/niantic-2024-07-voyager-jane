-- 8. The smallest line item amount made in Europe
-- (Use the customer_orders view)

-- (1 row) $4.80

USE northwind;

SELECT *
FROM customer_orders;

SELECT MIN(sales_price * quantity)
FROM customer_orders
ORDER BY (sales_price * quantity);


