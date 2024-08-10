package com.niantic.services;

import com.niantic.models.SubCategory;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;

public class SubCategoryDao
{
    private final JdbcTemplate jdbcTemplate;

    public SubCategoryDao()
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


    public ArrayList<SubCategory> getAllSubCategories()
    {
        ArrayList<SubCategory> subCategories = new ArrayList<>();

        String sql = """
                SELECT sub_category_id
                    , category_id
                    , sub_category_name
                    , description
                FROM sub_categories;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);

        while (row.next())
        {
            int subCategoryId = row.getInt("sub_category_id");
            int categoryId = row.getInt("category_id");
            String subCategoryName = row.getString("sub_category_name");
            String description = row.getString("description");

            SubCategory subCategory = new SubCategory(subCategoryId, categoryId, subCategoryName, description);

            subCategories.add(subCategory);
        }

        return subCategories;
    }


    public SubCategory getSubCategoryById(int id)
    {
        SubCategory subCategory = null;

        String sql = """
                SELECT sub_category_id
                    , category_id
                    , sub_category_name
                    , description
                FROM sub_categories
                WHERE sub_category_id = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, id);

        if (row.next())
        {
            int subCategoryId = row.getInt("sub_category_id");
            int categoryId = row.getInt(("category_id"));
            String subCatName = row.getString("sub_category_name");
            String description = row.getString("description");

            subCategory = new SubCategory(subCategoryId, categoryId, subCatName, description);
        }

        return subCategory;
    }


    public SubCategory getSubCategoryByName(String name)
    {
        SubCategory subCategory = null;

        String sql = """
                SELECT sub_category_id
                    , category_id
                    , sub_category_name
                    , description
                FROM sub_categories
                WHERE sub_category_name = ?;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, name);

        if (row.next())
        {
            int subCategoryId = row.getInt("sub_category_id");
            int categoryId = row.getInt(("category_id"));
            String subCatName = row.getString("sub_category_name");
            String description = row.getString("description");

            subCategory = new SubCategory(subCategoryId, categoryId, subCatName, description);
        }

        return subCategory;
    }


    public void addSubCategory(SubCategory subCategory)
    {
        String sql = """
                INSERT INTO sub_categories
                (
                    sub_category_id
                    , category_id
                    , sub_category_name
                    , description
                )
                VALUES (?, ?, ?, ?);
                """;

        jdbcTemplate.update(sql,
                subCategory.getSubCategoryId(),
                subCategory.getCategoryId(),
                subCategory.getName(),
                subCategory.getDescription());
    }



}
