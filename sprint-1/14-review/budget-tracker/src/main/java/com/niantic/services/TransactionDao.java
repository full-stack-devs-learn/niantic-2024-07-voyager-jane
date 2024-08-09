package com.niantic.services;

import com.niantic.models.Transaction;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;

public class TransactionDao
{
    private final JdbcTemplate jdbcTemplate;

    public TransactionDao()
    {
        String databaseUrl = "jdbc:mysql://localhost:3306/budget";
        String userName = "root";
        String passWord = "P@ssw0rd";

        DataSource dataSource = new BasicDataSource()
        {{
            setUrl(databaseUrl);
            setUsername(userName);
            setPassword(passWord);
        }};

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addTransaction(Transaction transaction)
    {

        String sql = """
                INSERT INTO transactions
                (
                    transaction_id
                    , user_id
                    , sub_category_id
                    , vendor_id
                    , transaction_date
                    , amount
                )
                VALUES (?, ?, ?, ?, ?, ?);
                """;

        jdbcTemplate.update(sql,
                transaction.getTransactionId(),
                transaction.getUserId(),
                transaction.getSubCategoryId(),
                transaction.getVendorId(),
                transaction.getTransactionDate(),
                transaction.getAmount());
    }


}
