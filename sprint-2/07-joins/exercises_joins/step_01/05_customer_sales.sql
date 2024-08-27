-- 5. The order details table includes the line item for each sale.
-- the Line Total is the SalesPrice * Quantity - Discount%.

-- I want a customer sales report
-- include customer names (compay_name), and their total sales amount (after discount)
-- sorted by sales total highest to lowest

-- The first 3 rows should be
-- CompanyName			SalesTotal
-- QUICK-Stop			110277.31
-- Ernst Handel			104874.98
-- Save-a-lot Markets	104361.95

-- (89 rows)

USE northwind;

SELECT c.company_name
	, SUM((od.unit_price * od.quantity) - (od.unit_price * od.quantity * od.discount)) AS total_sales
FROM customers AS c
INNER JOIN orders AS o ON c.customer_id = o.customer_id
INNER JOIN order_details AS od ON o.order_id = od.order_id
GROUP BY c.company_name
ORDER BY total_sales DESC;
