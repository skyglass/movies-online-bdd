package com.doublegrooverecords.vinyl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StoreRepository {
    JdbcTemplate jdbcTemplate;

    public StoreRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Store> findByZip(String zip) {
        return jdbcTemplate.query("select id, street_address_1, street_address_2, " +
                "state_name, city, zip " +
                "from mrt_location " +
                "where zip = ?", new RowMapper<Store>() {
            @Override
            public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Store(rs.getLong("id"), rs.getString("street_address_1"), rs.getString("street_address_2"), rs.getString("state_name"), rs.getString("city"), rs.getString("zip"));
            }
        }, zip);
    }

    public Store first() {
        return jdbcTemplate.queryForObject("select id, street_address_1, street_address_2, " +
                "state_name, city, zip " +
                "from mrt_location " +
                "limit 1;", new RowMapper<Store>() {
            @Override
            public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Store(rs.getLong("id"), rs.getString("street_address_1"), rs.getString("street_address_2"), rs.getString("state_name"), rs.getString("city"), rs.getString("zip"));
            }
        });
    }
}
