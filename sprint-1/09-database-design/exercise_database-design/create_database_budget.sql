START TRANSACTION;

DROP DATABASE IF EXISTS budget;

CREATE DATABASE budget;

USE budget;

CREATE TABLE transactions
(
	transaction_id INT AUTO_INCREMENT
    , transaction_date DATE NOT NULL
    , person_name VARCHAR(50) NOT NULL
    , category_id INT NOT NULL
    , sub_category_id INT NOT NULL
    , vendor_name VARCHAR(50) NULL
    , amount DECIMAL(10, 2) NOT NULL
	, notes TEXT(250) 
    , PRIMARY KEY (transaction_id)
    , FOREIGN KEY (category_id) REFERENCES categories(category_id)
    , FOREIGN KEY (sub_category_id) REFERENCES sub_categories(sub_category_id)
);

CREATE TABLE categories
(
	category_id INT AUTO_INCREMENT
    , category_name VARCHAR(50) NOT NULL
    , PRIMARY KEY (category_id)
);

CREATE TABLE sub_categories
(
	sub_category_id INT AUTO_INCREMENT
    , category_id INT NOT NULL
    , total_amount DECIMAL(10,2) NOT NULL
    , PRIMARY KEY (sub_category_id)
    , FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

ROLLBACK;