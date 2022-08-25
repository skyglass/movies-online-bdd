package com.doublegrooverecords.vinyl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CustomerRepository {
    JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Customer findById(Long id) {
        return jdbcTemplate.queryForObject("select id, username, password, " +
                "email_address, street_address_1, " +
                "street_address_2, state_name, " +
                "city, zip from mrt_customer " +
                "where id = ?", new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Customer(rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email_address"),
                        rs.getString("street_address_1"),
                        rs.getString("street_address_2"),
                        rs.getString("state_name"),
                        rs.getString("city"),
                        rs.getString("zip"));
            }
        }, id);
    }

    public void update(Customer customer) {
        jdbcTemplate.update("update mrt_customer " +
                "set username = ?, " +
                " password = ?, " +
                " email_address = ?, " +
                " street_address_1 = ?, " +
                " street_address_2 = ?, " +
                " state_name = ?, " +
                " city = ?, " +
                " zip = ? " +
                        "where id = ?",
                customer.getUsername(),
                customer.getPassword(),
                customer.getEmailAddress(),
                customer.getStreetAddress1(),
                customer.getStreetAddress2(),
                customer.getStateName(),
                customer.getCity(),
                customer.getZip(),
                customer.getId()
                );
    }
}
