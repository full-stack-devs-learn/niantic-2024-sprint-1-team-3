package com.niantic.services;

import com.niantic.models.Transaction;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.jdbc.Sql;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TransactionDao
{
    private final JdbcTemplate jdbcTemplate;

    public TransactionDao()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/budget");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ArrayList<Transaction> getTransactionByUser(int userId)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();

        String sql = """
                SELECT transaction_id
                    , user_id
                    , category_id
                    , vendor_id
                    , transaction_date
                    , amount
                    , notes)
                FROM transactions
                WHERE user_id = ?;
                """;

        var row = jdbcTemplate.queryForRowSet(sql, userId);

        return rowActions(row);
    }

    

    public void addTransaction(Transaction transaction)
    {
        String sql = """
                INSERT INTO transactions
                (user_id
                , category_id
                , vendor_id
                , transaction_date
                , amount
                , notes)
                VALUES
                (?, ?, ?, ?, ?, ?);
                """;

        jdbcTemplate.update(sql
                , transaction.getUserId()
                , transaction.getCategoryId()
                , transaction.getVendorId()
                , transaction.getTransactionDate()
                , transaction.getAmount()
                , transaction.getNotes());
    }
}
