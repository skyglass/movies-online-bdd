package com.doublegrooverecords.vinyl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class SalesRecordsRepository {
    JdbcTemplate jdbcTemplate;

    public SalesRecordsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getSalesData() {
        List<Order> orders = jdbcTemplate.query("select " +
                "id, " +
                "customer_code, " +
                "created_on, " +
                "billed_on, " +
                "store_id, " +
                "shipping_cost, " +
                "street_address_1, " +
                "street_address_2, " +
                "state_name, " +
                "city, " +
                "zip " +
                "from mrt_order", new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Order(rs.getLong("id"),
                        rs.getLong("customer_code"),
                        rs.getString("street_address_1"),
                        rs.getString("street_address_2"),
                        rs.getString("state_name"),
                        rs.getString("city"),
                        rs.getString("zip"),
                        rs.getBigDecimal("shipping_cost"),
                        null,
                        Collections.emptyList());
            }
        });
        return orders.size();
    }
}
