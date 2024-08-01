USE northwind;

-- Write a script that updates the price of all products 
-- within a category by a certain percent.
-- use variables to accept the Category name and the percent increase 



-- CategoryName & Percentage Increase User Input
SET @up_category_name = 'Sporting Goods';
SET @percent_increase = 0.25;



-- Finding CategoryID from CategoryName User Input
SELECT @up_category_id := category_id
FROM categories
WHERE category_name = @up_category_name;

-- Viewing/Making Sure WHERE is going to update the right rows
SELECT *
FROM products
WHERE category_id = @up_category_id;

-- Updating Products 
UPDATE products
SET unit_price = unit_price * (1 + @percent_increase)
WHERE category_id = @up_category_id;

-- View Change
SELECT *
FROM products
WHERE category_id = @up_category_id;