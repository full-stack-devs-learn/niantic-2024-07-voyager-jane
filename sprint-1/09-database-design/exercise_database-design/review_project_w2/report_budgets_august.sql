USE budget;

-- Report on August Expenses per Category
SELECT sub_categories.category_id
, SUM(amount) AS august_categories_amount
FROM transactions
INNER JOIN sub_categories ON transactions.sub_category_id = sub_categories.sub_category_id
WHERE MONTH(transaction_date) = 8
GROUP BY category_id;


-- Report on Expenses for a Category : Bills
-- This is August expenses in Bills Category. Can test on July as Well.
SELECT sub_category_name
	, SUM(amount) AS august_bills_amount
FROM sub_categories
INNER JOIN transactions on sub_categories.sub_category_id = transactions.sub_category_id
WHERE category_id = 2 AND MONTH(transaction_date) = 8
GROUP BY sub_category_name;

-- This is all expenses in Bills Category
SELECT sub_category_name
	, transaction_date
	, amount
FROM sub_categories
INNER JOIN transactions on sub_categories.sub_category_id = transactions.sub_category_id
WHERE category_id = 2
ORDER BY transaction_date;
