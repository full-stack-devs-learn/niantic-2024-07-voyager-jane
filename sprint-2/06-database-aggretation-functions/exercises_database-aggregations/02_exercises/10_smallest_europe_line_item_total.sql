-- 8. The smallest line item amount made in Europe
-- (Use the customer_orders view)

-- (1 row) $4.80

USE northwind;

SELECT MIN(sales_price * quantity) AS europe_min_line_item_amount
FROM customer_orders
WHERE country IN ('Germany', 'Switzerland', 'France', 'Portugal', 'Finland', 'Poland', 'Ireland', 'Sweden', 'UK');


