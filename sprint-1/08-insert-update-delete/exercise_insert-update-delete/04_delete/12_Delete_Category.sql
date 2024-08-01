USE northwind;

-- Delete the Sporting Goods Catregory from the database.

-- Why does it fail to delete?

-- What else needs to be done to fully delete the Category?

-- Write a script that preforms all necessary steps to 
-- delete the category from the database.

-- The only user input should be a variable at the top of the 
-- script that holds the Category Name.
-- The sript should do the rest


-- First, delete all rows from products table that contain the category Sporting Goods (children rows?).

-- Then, delete Sporting Good category from categories table (parent row?).

SELECT @sport_category := category_id
FROM categories
WHERE category_name = 'Sporting Goods';

SELECT *
FROM products
WHERE category_id = @sport_category;

DELETE FROM products
WHERE category_id = @sport_category;

DELETE FROM categories
WHERE category_name = 'Sporting Goods'; 

SELECT * 
FROM categories;