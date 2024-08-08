package com.niantic.services;

import com.niantic.models.Category;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class CategoryDao
{
    private final JdbcTemplate jdbcTemplate;

    public CategoryDao()
    {
        String databaseUrl = "jdbc:mysql//localhost:3306/budget";
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

    public void addCategory(Category category)
    {
        String sql = """
                INSERT INTO categories
                (
                    category_id
                    , category_name
                    , description
                )
                VALUES (?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                category.getCategoryId(),
                category.getName(),
                category.getDescription());

    }
}
