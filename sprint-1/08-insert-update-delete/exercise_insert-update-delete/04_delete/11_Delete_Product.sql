USE northwind;

-- delete one of your new sporting goods products
-- from the database
-- choose one that has not been purchased 

SELECT @sport_category := category_id
FROM categories
WHERE category_name = 'Sporting Goods';

SELECT @sport_category;



-- SELECT *
-- FROM products
-- WHERE category_id = ;