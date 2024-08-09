package com.niantic.services;

import com.niantic.models.Vendor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;

public class VendorDao
{
    private final JdbcTemplate jdbcTemplate;

    public VendorDao()
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

    public ArrayList<Vendor> getAllVendors()
    {
        ArrayList<Vendor> vendors = new ArrayList<>();

        String sql = """
                SELECT vendor_id
                    , vendor_name
                    , website
                FROM vendors;
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);

        while (row.next())
        {
            int vendorId = row.getInt("vendor_id");
            String vendorName = row.getString("vendor_name");
            String website = row.getString("website");

            Vendor vendor = new Vendor(vendorId, vendorName, website);

            vendors.add(vendor);
        }

        return vendors;
    }

    public void addVendor(Vendor vendor)
    {
        String sql = """
                INSERT INTO vendors
                (
                    vendor_id
                    , vendor_name
                    , website
                )
                VALUES (?, ?, ?);
                """;

        jdbcTemplate.update(sql, vendor.getVendorId(), vendor.getName(), vendor.getWebsite());
    }
}
