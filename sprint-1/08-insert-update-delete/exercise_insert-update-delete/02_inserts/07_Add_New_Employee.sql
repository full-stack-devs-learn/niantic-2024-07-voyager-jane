USE northwind;

-- You were just hired by Northwind Traders, Inc and 
-- you need to add yourself as a Sales Associate to the Employees table.
-- Inlcude your:
    -- full name
    -- job title
    -- preferred title (Mr, Mrs, etc)
    -- Birthday
    -- hire date: (today)
    -- home address
-- leave all other fields null by default

-- NOTE: It won't let me insert without a notes column so I added a blank string to notes to make the insert call pass
INSERT INTO employees
(
	last_name
    , first_name
    , title
    , title_of_courtesy
    , birth_date
    , hire_date
    , address
    , city
    , region
    , postal_code
    , country
    , notes
)
VALUES
(
	'Huynh'
    , 'Jane'
    , 'Sales Associate'
    , 'Ms.'
    , '2000-10-01 00:00:00'
    , '2024-07-31 00:00:00'
    , '1005 Lincoln St.'
    , 'Bellevue'
    , 'WA'
    , 90001
    , 'USA'
    , ''
);

