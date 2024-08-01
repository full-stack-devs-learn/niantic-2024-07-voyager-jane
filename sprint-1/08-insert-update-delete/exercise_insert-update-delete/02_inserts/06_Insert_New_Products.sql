USE northwind;

-- Insert 10 new products into the new Sporting Goods Category
-- You can leave the Supplier Id empty for now, but you need to include
-- the product name, category, price, units in stock (20), units on order (0)
-- and re-order level (10) for each product.

INSERT INTO products
(
	product_name
    , category_id
    , unit_price
    , units_in_stock
    , units_on_order
    , reorder_level
)
VALUE ('Badminton Birdie', @new_id, 8.00, 20, 0, 10)
	, ('Badminton Racket', @new_id, 45.00, 20, 0, 10)
    , ('Badminton Net', @new_id, 35.00, 20, 0, 10)
    , ('Tennis Ball', @new_id, 15.00, 20, 0, 10)
    , ('Tennis Racket', @new_id, 75.00, 20, 0, 10)
    , ('Tennis Net', @new_id, 45.00, 20, 0, 10)
    , ('Ping Pong Ball', @new_id, 5.00, 20, 0, 10)
    , ('Ping Pong Paddle', @new_id, 25.00, 20, 0, 10)
    , ('Ping Pong Table', @new_id, 355.00, 20, 0, 10)
    , ('Running Shoes', @new_id, 175.00, 20, 0, 10);


-- View Insert queries
SELECT *
FROM products
WHERE category_id = @new_id;