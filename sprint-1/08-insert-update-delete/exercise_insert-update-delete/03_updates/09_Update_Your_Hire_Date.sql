USE northwind;

-- write an update statement and change your hire date to 
-- Monday 2 weeks ago

UPDATE employees
SET hire_date = '2024-07-15 00:00:00'
WHERE last_name = 'Huynh';

SELECT *
FROM employees
WHERE last_name = 'Huynh';