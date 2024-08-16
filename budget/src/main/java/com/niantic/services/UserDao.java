package com.niantic.services;

import com.niantic.models.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao
{
    private final JdbcTemplate jdbcTemplate;

    public UserDao()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/budget");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addUser(User user)
    {
        String sql = """
                INSERT INTO users
                (user_name
                , first_name
                , last_name
                , phone
                , email)
                VALUES
                (?, ?, ?, ?, ?);
                """;

        jdbcTemplate.update(sql
                , user.getUserName()
                , user.getFirstName()
                , user.getLastName()
                , user.getPhone()
                , user.getEmail());
    }
}
