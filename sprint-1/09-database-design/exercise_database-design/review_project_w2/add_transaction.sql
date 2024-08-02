USE budget;

-- Transaction: date, person, subcategory, vendor, amount, notes
-- User Input Here for Transaction
SET @add_date = '2023-06-25'
	, @person = 'Jane'
    , @subcategory = 'Rent'
    , @vendor = 'Landlord'
    , @amount = 2150.00
    , @notes = 'sadge';
    

-- Finding the user input in the database
SELECT @person_id := person_id
FROM person
WHERE person_name = @person;

SELECT @sub_category_id := sub_category_id
FROM sub_categories
WHERE sub_category_name = @subcategory;

SELECT @vendor_id := vendor_id
FROM vendors
WHERE vendor_name = @vendor;


-- Inserting Transaction into database
INSERT INTO transactions
(
	transaction_date
	, person_id
    , sub_category_id
    , vendor_id
    , amount
    , notes
)
VALUES
(
	@add_date
    , @person_id
    , @sub_category_id
    , @vendor_id
    , @amount
    , @notes
);


-- Check if Transaction was Added
SELECT *
FROM transactions
ORDER BY transaction_id DESC;