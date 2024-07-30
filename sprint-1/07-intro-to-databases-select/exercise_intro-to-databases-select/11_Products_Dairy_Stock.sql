USE northwind;

-- The Product name, price and the number
-- of units available for sale (in stock + on order)
-- of all dairy products

-- Hint: Be sure to create a column alias for 
-- the new column

-- Expected: 10 rows

SELECT product_name
	, unit_price
    , units_in_stock + units_on_order AS units_available_for_sale
FROM products
WHERE category_id = 4;