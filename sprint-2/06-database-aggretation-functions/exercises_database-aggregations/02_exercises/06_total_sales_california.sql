-- 6. The sum of all sales in California
-- sales_price * quantity -- don't worry about discount
-- (Use the customer_orders view)

-- (1 row) - $3490.02

USE northwind;

SELECT SUM(sales_price * quantity) AS ca_total_sales
FROM customer_orders
WHERE region = 'CA';


