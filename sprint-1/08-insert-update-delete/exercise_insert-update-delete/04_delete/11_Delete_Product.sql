USE northwind;

-- delete one of your new sporting goods products
-- from the database
-- choose one that has not been purchased 

SELECT @sport_category := category_id
FROM categories
WHERE category_name = 'Sporting Goods';

SELECT *
FROM products
WHERE category_id = @sport_category;

DELETE FROM products
WHERE product_name = 'Running Shoes';

-- Checking to make sure running shoes was deleted 
SELECT *
FROM products
WHERE category_id = @sport_category;
