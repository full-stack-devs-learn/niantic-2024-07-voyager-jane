-- Create a full script with variables that allows you 
-- to add a new Customer into the database. Then create an order for them
-- that includes 5 products. Create 5 variables at the top of the script
-- that store the 5 product names the customer wants to buy. You will use
-- the product names to find the id

-- Requirements
/*
    I should be able to run the script on my machine without having to
    write any queries to look up the id's of any tables. (The only id that
    I should have to know is the new CustomerId - which is a 5 letter code 
    that we will choose )

    Use variables at the top of the script to collect User Input. The rest 
    of the script should run without hardcoding ANY values.

    I.E. if I want to add a new customer, and order, I should be able 
    to change the variables at the beginning of the script, and run the whole
    script.
*/

/*
Customer must include: 
    Customer Id: 5 letter code
    Company Name: (You can use chatgpt or https://businessnamegenerator.com to pick a name)
    Contact Name: (you can use chatgpt or http://random-name-generator.info to pick a name)
    Address
    City
    Region
    Postal Code
    Country

Order:
    Order id: auto generated
    Customer Id: id from the inserted customer
    Order Date: today's date
    Ship Name: Contact name
    Ship Address: The company address information

OrderDetails: (Create 5 line items)
    Order Id: the one created above
    Product Id: use the product names to select/find the id of each product
    Unit Price: use the default list price of each product
    Quantity: you decide - between 1-10
    Discount: 0
*/


-- Setting Product ID Variables and Customer Code(ID)
SET @product_id1 = 1;
SET @product_id2 = 2;
SET @product_id3 = 3;
SET @product_id4 = 4;
SET @product_id5 = 5;

SET @customer_code = 'JNEDO';


-- Creating a New Customer
INSERT INTO customers
(
	customer_id
    , company_name
    , contact_name
    , address
    , city
    , region
    , postal_code
	, country
)
VALUES 
(
	@customer_code
    , 'Quality Convenience Mart'
    , 'Bob Smith'
    , '787 Washington Ave'
    , 'Tacoma'
    , 'WA'
    , '90012'
    , 'USA'
);


-- Inserting the Customer's Order
INSERT INTO orders 
(
	customer_id
    , order_date
    , ship_name
    , ship_address
    , ship_city
    , ship_region
    , ship_postal_code
    , ship_country
)
VALUES 
(
	@customer_code
    , '2024-07-31 00:00:00'
    , 'Bob Smith'
    , '787 Washington Ave'
    , 'Tacoma'
    , 'WA'
    , '90012'
    , 'USA'
);


 -- Setting Variables to Find the Order ID and the product prices
SET @new_order_id = LAST_INSERT_ID();

SELECT @price_1 := unit_price
FROM products
WHERE product_id = @product_id1;

SELECT @price_2 := unit_price
FROM products
WHERE product_id = @product_id2;

SELECT @price_3 := unit_price
FROM products
WHERE product_id = @product_id3;

SELECT @price_4 := unit_price
FROM products
WHERE product_id = @product_id4;

SELECT @price_5 := unit_price
FROM products
WHERE product_id = @product_id5;


-- Inserting the Order Details of Customer's Order using the variables we created
INSERT INTO order_details 
(
	order_id
    , product_id
    , unit_price
    , quantity
    , discount
)
VALUES 
	(@new_order_id, @product_id1, @price_1, 5, 0)
	, (@new_order_id, @product_id2, @price_2, 7, 0)
    , (@new_order_id, @product_id3, @price_3, 2, 0)
    , (@new_order_id, @product_id4, @price_4, 10, 0)
    , (@new_order_id, @product_id5, @price_5, 4, 0);
