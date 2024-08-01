USE northwind;

-- Write a script that updates the price of all products 
-- within a category by a certain percent.
-- use variables to accept the Category name and the percent increase 

-- Category_id 2 -> Aniseed Syrup og price is 10.00, Cajun Seasoning is 22.00
SET @category_id = 2;
SET @percent_increase = 0.25;

UPDATE products
SET unit_price = unit_price * (1 + @percent_increase)
WHERE category_id = @category_id;

-- View Change
SELECT *
FROM products
WHERE category_id = @category_id;