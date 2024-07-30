USE northwind;

-- The employee name and title
-- of all employees who were hired
-- in 2013

-- Expected: 3 rows

SELECT CONCAT(first_name, ' ', last_name) AS full_name
    , title
FROM employees
WHERE hire_date LIKE '2013%';