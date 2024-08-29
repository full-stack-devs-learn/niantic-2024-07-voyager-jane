-- 7. expensive products report

-- Display the most expensive product in each category

-- Columns to include:
-- Category Name
-- Product Name
-- Unit price

-- NOTE: you can combine INNER JOIN and sub queries in the
-- query if you would like

-- (8 rows - one per category)
-- Beverages        Cte de Blaye            263.5000
-- Condiments       Vegie-spread            43.9000
-- Confections      Sir Rodney's Marmalade  81.0000
-- Dairy Products   Raclette Courdavault    55.0000
-- Grains/Cereals   Gnocchi di nonna Alice  38.0000
-- Meat/Poultry     Thringer Rostbratwurst  123.7900
-- Produce          Manjimup Dried Apples   53.0000
-- Seafood          Carnarvon Tigers        62.5000


USE northwind;

SELECT category_name
	, p.product_name
    , p1.most_expensive
FROM categories AS c
INNER JOIN products AS p ON c.category_id = p.category_id
INNER JOIN (
					SELECT MAX(unit_price) AS most_expensive
						, category_id
					FROM products
					GROUP BY category_id
			) AS p1 ON p1.category_id = c.category_id
-- INNER JOIN (	
-- 					SELECT product_name
-- 							, unit_price
--                             , category_id
-- 					FROM products
-- 			) AS p2 ON p1.most_expensive = p2.unit_price
-- 				AND p2.category_id = c.category_id
WHERE p.unit_price = p1.most_expensive
ORDER BY category_name;



-- My og way to do this was not have the first inner join products AS p. Then after p1 have the Inner Join below this comment. And didnt have where = p.unit_price = p1. most_expensive:
-- INNER JOIN (	
-- 					SELECT product_name
-- 							, unit_price
--                             , category_id
-- 					FROM products
-- 			) AS p2 ON p1.most_expensive = p2.unit_price
-- 				AND p2.category_id = c.category_id

