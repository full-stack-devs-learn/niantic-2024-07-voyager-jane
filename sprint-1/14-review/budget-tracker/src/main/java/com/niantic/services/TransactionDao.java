package com.niantic.services;

import com.niantic.models.Transaction;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Date;
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


    public ArrayList<Transaction> getTransactionByUser(int id)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();

        String sql = """
                SELECT transactions.transaction_id
                    , transactions.user_id
                    , transactions.sub_category_id
                    , transactions.vendor_id
                    , transactions.transaction_date
                    , transactions.amount
                    , transactions.notes
                FROM transactions
                INNER JOIN users ON transactions.user_id = users.user_id
                WHERE transactions.user_id = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, id);

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


    public ArrayList<Transaction> getTransactionByMonth(int month)
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
                FROM transactions
                WHERE MONTH(transaction_date) = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, month);

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


    public ArrayList<Transaction> getTransactionByYear(int year)
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
                FROM transactions
                WHERE YEAR(transaction_date) = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, year);

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


    public ArrayList<Transaction> getTransactionBySubCategory(int id)
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
                FROM transactions
                WHERE sub_category_id = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, id);

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


    public ArrayList<Transaction> getTransactionByCategory(int id)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();

        String sql = """
                SELECT transactions.transaction_id
                    , transactions.user_id
                    , transactions.sub_category_id
                    , transactions.vendor_id
                    , transactions.transaction_date
                    , transactions.amount
                    , transactions.notes
                FROM transactions
                INNER JOIN sub_categories ON transactions.sub_category_id = sub_categories.sub_category_id
                WHERE sub_categories.category_id = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, id);

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
                    , notes
                )
                VALUES (?, ?, ?, ?, ?, ?, ?);
                """;

        jdbcTemplate.update(sql,
                transaction.getTransactionId(),
                transaction.getUserId(),
                transaction.getSubCategoryId(),
                transaction.getVendorId(),
                transaction.getTransactionDate(),
                transaction.getAmount(),
                transaction.getNotes());
    }


}
