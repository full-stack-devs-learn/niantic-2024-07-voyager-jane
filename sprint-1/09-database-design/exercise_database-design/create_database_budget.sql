DROP DATABASE IF EXISTS budget;

CREATE DATABASE budget;

USE budget;

-- FIRST CREATE THE PARENT TABLES THAT ONLY HAVE PRIMARY KEYS THEN CREATE THE TABLES THAT HAVE FOREIGN KEYS. 
-- or else it errors out and says the table doesn't exist since the script hasn't gone to the code that would create the children rows/tables?
CREATE TABLE person
(
	person_id INT AUTO_INCREMENT
    , person_name VARCHAR(50) NOT NULL
    , PRIMARY KEY (person_id)
);

CREATE TABLE categories
(
	category_id INT AUTO_INCREMENT
    , category_name VARCHAR(50) NOT NULL
    , description TEXT(100) NULL
    , PRIMARY KEY (category_id)
);

CREATE TABLE sub_categories
(
	sub_category_id INT AUTO_INCREMENT
    , sub_category_name VARCHAR(50) NOT NULL
    , category_id INT NOT NULL
    , PRIMARY KEY (sub_category_id)
    , FOREIGN KEY (category_id) REFERENCES categories(category_id)
);


CREATE TABLE vendors
(
	vendor_id INT AUTO_INCREMENT
    , vendor_name VARCHAR(50) NOT NULL
    , phone VARCHAR(20) NULL
    , website VARCHAR(150) NULL
    , PRIMARY KEY (vendor_id)
);

CREATE TABLE transactions
(
	transaction_id INT AUTO_INCREMENT
    , transaction_date DATE NOT NULL
    , person_id INT NOT NULL
    , sub_category_id INT NOT NULL
    , vendor_id INT NOT NULL
    , amount DECIMAL(10, 2) NOT NULL
	, notes TEXT(250) 
    , PRIMARY KEY (transaction_id)
    , FOREIGN KEY (person_id) REFERENCES person(person_id)
    , FOREIGN KEY (sub_category_id) REFERENCES sub_categories(sub_category_id)
    , FOREIGN KEY (vendor_id) REFERENCES vendors(vendor_id)
);

/* 
Starting Data for August (July $ +10) - Categories (Subcategories | Vendor | Amount | Note): 
    - Food
		- Groceries | SafeMart | 85.00 | 2 weeks worth
        - Groceries | Trader John | 60.00 | 1.5 week worth
        - Snacks | SafeMart | 15.00 | Chips and Dips
        - Water Filter Refill | TarForGet | 8.00
    - Bills
		- Rent | Landlord | 2500.00
        - Internet | FastSpeeds | 50.00
        - Utilities | WaterReserve | 50.00
        - Utilities | PowerShell | 50.00
        - Cell Phone | VMobile | 55.00
        - Car Insurance | Region Farm | 120.00
    - Transportation
		- Gas | CostGo | $45.00
        - Bus Pass | CityCruise | $25.00
        - Gas | CostGo | $47.00
        - Subway Ticket | UnderTravel | $3.00
    - Healthcare
		- Shampoo | CostGo | 21.00
        - Vitamins | CostGo | 25.00
    - Friends and Family
		- Eating Out | YummyBistro | 25.00 | Eating with Ellie
        - Eating Out | Pho 86 | 85.00 | Eating with the fam
        - Birthday Gift | TarForGet | 35.00 | Fern's Birthday
    - Hobbies: 
		- Notflix | Notflix | 12.00
        - Book | MagicalBooks | 14.00
*/


-- START INSERTING DATA INTO THE TABLES. Again starting with parent tables.
INSERT INTO person (person_id, person_name) VALUES (1, 'Jane');

INSERT INTO categories 
(
	category_id
    , category_name
	, description
) 
VALUES 
    (1, 'Food', 'I need food and water to survive.')
    , (2, 'Bills', 'Life ain''t free. I got bills, I gotta pay.')
    , (3, 'Transportation', 'Car & Public Transport')
    , (4, 'Healthcare', 'Medical visits, Healthcare, & Hygiene products')
    , (5, 'Friends and Family', 'Keeping track of how much I spend while hanging out with friends and family.')
    , (6, 'Hobbies', 'Money spent on things to have fun and have enrichment in life. "Don''t worry, I still have $3 left in my account."');

INSERT INTO sub_categories 
(
	sub_category_id
    , sub_category_name
    , category_id
) 
VALUES 
	(1, 'Groceries', 1)
    , (2, 'Snacks', 1)
    , (3, 'Water Filter Refill', 1)
    , (4, 'Rent', 2)
    , (5, 'Internet', 2)
    , (6, 'Utilities', 2)
    , (7, 'Cell Phone', 2)
    , (8, 'Car Insurance', 2)
    , (9, 'Gas', 3)
    , (10, 'Bus Pass', 3)
    , (11, 'Subway Ticket', 3)
    , (12, 'Shampoo', 4)
    , (13, 'Vitamins', 4)
    , (14, 'Eating Out', 5)
    , (15, 'Birthday Gift', 5)
    , (16, 'Notflix', 6)
    , (17, 'Book', 6);

INSERT INTO vendors (
	vendor_id
    , vendor_name
) 
VALUES 
	(1, 'SafeMart')
    , (2, 'Trader John')
    , (3, 'TarForGet')
    , (4, 'Landlord')
    , (5, 'FastSpeeds')
    , (6, 'WaterReserve')
    , (7, 'PowerShell')
    , (8, 'VMobile')
    , (9, 'Region Farm')
    , (10, 'CostGo')
    , (11, 'CityCruise')
    , (12, 'UnderTravel')
    , (13, 'YummyBistro')
    , (14, 'Pho 86')
    , (15, 'TarForGet')
    , (16, 'Notflix')
    , (17, 'MagicalBooks');

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
	-- month of July
	(DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 1, 1, 105.00, '2.5 weeks worth') -- Groceries
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 1, 2, 40.00, '1 weeks worth') -- Groceries
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 2, 1, 25.00, 'crackers and cheese') -- Snacks
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 3, 3, 12.00, NULL) -- Water Filter
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 4, 4, 2200.00, NULL) -- Rent
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 5, 5, 60.00, NULL) -- Internet
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 6, 6, 60.00, NULL) -- Utilities
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 6, 7, 55.00, NULL) -- Utilities
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 7, 8, 65.00, NULL) -- Cell Phone
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 8, 9, 150.00, NULL) -- Car Insurance
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 9, 10, 55.00, NULL) -- Gas
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 10, 11, 15.00, NULL) -- Bus Pass
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 9, 10, 37.00, NULL) -- Gas
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 11, 12, 6.00, NULL) -- Subway Ticket
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 12, 10, 15.00, NULL) -- Shampoo
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 13, 10, 20.00, NULL) -- Vitamins
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 14, 13, 35.00, 'Eating with Ash') -- Eating Out
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 14, 14, 50.00, 'Eating with lil sis') -- Eating Out
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 15, 3, 25.00, 'Tim''s Birthday') -- Birthday Gift
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 16, 16, 12.00, NULL) -- Notflix
    , (DATE_ADD(CURRENT_DATE, INTERVAL -1 MONTH), 1, 17, 17, 20.00, NULL) -- Book
    
	-- month of August
	, (CURRENT_DATE, 1, 1, 1, 85.00, '2 weeks worth') -- Groceries
    , (CURRENT_DATE, 1, 1, 2, 60.00, '1.5 weeks worth') -- Groceries
    , (CURRENT_DATE, 1, 2, 1, 15.00, 'Chips and dips') -- Snacks
    , (CURRENT_DATE, 1, 3, 3, 8.00, NULL) -- Water Filter
    , (CURRENT_DATE, 1, 4, 4, 2500.00, NULL) -- Rent
    , (CURRENT_DATE, 1, 5, 5, 50.00, NULL) -- Internet
    , (CURRENT_DATE, 1, 6, 6, 50.00, NULL) -- Utilities
    , (CURRENT_DATE, 1, 6, 7, 50.00, NULL) -- Utilities
    , (CURRENT_DATE, 1, 7, 8, 55.00, NULL) -- Cell Phone
    , (CURRENT_DATE, 1, 8, 9, 120.00, NULL) -- Car Insurance
    , (CURRENT_DATE, 1, 9, 10, 45.00, NULL) -- Gas
    , (CURRENT_DATE, 1, 10, 11, 25.00, NULL) -- Bus Pass
    , (CURRENT_DATE, 1, 9, 10, 47.00, NULL) -- Gas
    , (CURRENT_DATE, 1, 11, 12, 3.00, NULL) -- Subway Ticket
    , (CURRENT_DATE, 1, 12, 10, 21.00, NULL) -- Shampoo
    , (CURRENT_DATE, 1, 13, 10, 25.00, NULL) -- Vitamins
    , (CURRENT_DATE, 1, 14, 13, 25.00, 'Eating with Ellie') -- Eating Out
    , (CURRENT_DATE, 1, 14, 14, 85.00, 'Eating with the fam') -- Eating Out
    , (CURRENT_DATE, 1, 15, 3, 35.00, 'Fern''s Birthday') -- Birthday Gift
    , (CURRENT_DATE, 1, 16, 16, 12.00, NULL) -- Notflix
    , (CURRENT_DATE, 1, 17, 17, 14.00, NULL); -- Book
    

-- LOOK AT THE TABLES TO DOUBLE CHECK EVERYTHING IS OK
SELECT *
FROM person;

SELECT *
FROM categories;

SELECT *
FROM vendors;

SELECT *
FROM sub_categories;

SELECT *
FROM transactions;
