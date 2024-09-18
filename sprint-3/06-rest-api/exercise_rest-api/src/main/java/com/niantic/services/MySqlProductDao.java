package com.niantic.services;

import com.niantic.models.Product;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductDao implements ProductDao
{
    private final JdbcTemplate jdbcTemplate;

    public MySqlProductDao()
    {
        String databaseUrl = "jdbc:mysql://localhost:3306/northwind";
        String userName = "root";
        String password = "P@ssw0rd";
        DataSource dataSource = new BasicDataSource(){{
            setUrl(databaseUrl);
            setUsername(userName);
            setPassword(password);
        }};

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId)
    {
        List<Product> products = new ArrayList<>();

        String sql = """
                SELECT product_id
                    , product_name
                    , quantity_per_unit
                    , unit_price
                    , units_in_stock
                    , units_on_order
                    , reorder_level
                FROM products
                WHERE category_id = ?;
                """;

        var row = jdbcTemplate.queryForRowSet(sql, categoryId);

        while (row.next())
        {
            int productId = row.getInt("product_id");
            String productName = row.getString("product_name");
            String quantityPerUnit = row.getString("quantity_per_unit");
            double unitPrice = row.getDouble("unit_price");
            int unitsInStock = row.getInt("units_in_stock");
            int unitsOnOrder = row.getInt("units_on_order");
            int reorderLevel = row.getInt("reorder_level");

            Product product = new Product(productId, categoryId, productName, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel);
            products.add(product);
        }

        return products;


    }

    @Override
    public Product getProductById(int productId)
    {
        Product product = new Product();

        String sql = """
                SELECT category_id
                    , product_name
                    , quantity_per_unit
                    , unit_price
                    , units_in_stock
                    , units_on_order
                    , reorder_level
                FROM products
                WHERE product_id = ?;
                """;

        var row = jdbcTemplate.queryForRowSet(sql, productId);

        if (row.next())
        {
            int categoryId = row.getInt("category_id");
            String productName = row.getString("product_name");
            String quantityPerUnit = row.getString("quantity_per_unit");
            double unitPrice = row.getDouble("unit_price");
            int unitsInStock = row.getInt("units_in_stock");
            int unitsOnOrder = row.getInt("units_on_order");
            int reorderLevel = row.getInt("reorder_level");

            product = new Product(productId, categoryId, productName, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel);
        }

        return product;
    }

    @Override
    public Product addProduct(Product product)
    {
        String sql = """
                INSERT INTO products (
                    category_id
                    , product_name
                    , quantity_per_unit
                    , unit_price
                    , units_in_stock
                    , units_ons_order
                    , reorder_level
                )
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, product.getCategoryId());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getQuantityPerUnit());
            statement.setDouble(4, product.getUnitPrice());
            statement.setInt(5, product.getUnitsInStock());
            statement.setInt(1, product.getUnitsOnOrder());
            statement.setInt(1, product.getReorderLevel());
            return statement;
        }, keyHolder);

        int newId = keyHolder.getKey().intValue();

        return getProductById(newId);
    }
}
