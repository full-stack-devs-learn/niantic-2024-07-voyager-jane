USE northwind;

-- Select the new Category id
SET @new_id = LAST_INSERT_ID();

SELECT last_insert_ID();