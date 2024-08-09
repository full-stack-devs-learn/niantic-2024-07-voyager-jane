package com.niantic.services;

import com.niantic.models.Transaction;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Date;
import java.time.LocalDate;
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

    public ArrayList<Transaction> getAllTransactions()
    {
        ArrayList<Transaction> transactions = new ArrayList<>();

        String sql = """
                SELECT transaction_id
                    , user_id
                    , sub_category_id
                    , vendor_id
                    , transaction_date
                    , amount
                    , notes
                FROM transactions;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);

        while (row.next())
        {
            int transactionId = row.getInt("transaction_id");
            int userId = row.getInt("user_id");
            int subCatId = row.getInt("sub_category_id");
            int vendorId = row.getInt("vendor_id");
            BigDecimal amount = row.getBigDecimal("amount");
            String notes = row.getString("notes");

            LocalDate transactionDate = null;
            Date convertDate = row.getDate("transaction_date");

            if (convertDate != null)
            {
                transactionDate = convertDate.toLocalDate();
            }

            Transaction transaction = new Transaction(transactionId, userId, subCatId, vendorId, transactionDate, amount, notes);

            transactions.add(transaction);
        }

        return transactions;
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
