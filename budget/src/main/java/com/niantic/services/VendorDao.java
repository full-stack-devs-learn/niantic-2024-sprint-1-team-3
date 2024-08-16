package com.niantic.services;

import com.niantic.models.Vendor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;

public class VendorDao {
    private JdbcTemplate jdbcTemplate;

    public VendorDao()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/budget");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

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

        while(row.next()){
            int vendorId = row.getInt("vendor_id");
            String vendorName = row.getString("vendor_name");
            String website = row.getString("website");

            Vendor vendor = new Vendor(vendorId,vendorName,website);

            vendors.add(vendor);
        }

        return vendors;
    }

    public Vendor getVendorById(int vendorId)
    {
        String sql = """
        SELECT vendor_id
               , vendor_name
               , website
        FROM vendors
        WHERE vendor_id = ?;
        """;
        var row = jdbcTemplate.queryForRowSet(sql, vendorId);

        if(row.next())
        {
            int id = row.getInt("vendor_id");
            String vendorName = row.getString("vendor_name");
            String website = row.getString("website");

            return new Vendor(id, vendorName, website);

        }
        return null;
    }

    public Vendor getVendorByName(String vendorName)
    {
        String sql = """
        SELECT vendor_id
               , vendor_name
               , website
        FROM vendors;
        WHERE vendor_id = ?;
        """;
        var row = jdbcTemplate.queryForRowSet(sql, vendorName);

        if(row.next())
        {
            int vendorId = row.getInt("vendor_id");
            String name = row.getString("vendor_name");
            String website = row.getString("website");

            return new Vendor(vendorId, name, website);

        }
        return null;
    }

    public void addVendor (Vendor vendor)
    {
        String sql = """
        INSERT INTO vendors
            (vendor_name
                , website)
        VALUES
        (?,?);
        """;
        jdbcTemplate.update(sql
                , vendor.getVendorName()
                , vendor.getWebsite()
        );
    }

    public void updateVendor (Vendor vendor)
    {
        String sql = """
        UPDATE vendors
        SET vendor_name = ?
               , website = ?
        WHERE vendor_id = ?;
        """;

        jdbcTemplate.update(sql
                , vendor.getVendorName()
                , vendor.getWebsite()
                , vendor.getVendorId()
        );
    }

    public void deleteVendor (int vendorId)
    {
        String sql = "DELETE FROM vendors WHERE vendor_id = ?;";

        jdbcTemplate.update(sql, vendorId);
    }

}
