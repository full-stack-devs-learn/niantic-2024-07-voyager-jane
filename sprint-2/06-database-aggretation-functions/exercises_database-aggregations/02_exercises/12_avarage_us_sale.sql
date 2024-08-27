-- 12. The average line_item amount for all sales in the US
-- (Use the customer_orders view)
-- expect approx $748.77

-- (1 row)

USE northwind;

SELECT ROUND(AVG(sales_price * quantity), 2) AS us_avg_line_item_amount
FROM customer_orders
WHERE country = 'USA';


