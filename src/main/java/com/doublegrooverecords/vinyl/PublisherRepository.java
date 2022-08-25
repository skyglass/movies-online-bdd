package com.doublegrooverecords.vinyl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PublisherRepository {
    JdbcTemplate jdbcTemplate;

    public PublisherRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Publisher findForId(Long id) {
        return jdbcTemplate.queryForObject("select id, name, short_name, contact_number, contact_name from manufacturer where id = ?", new PublisherMapper(), id);
    }

    public List<Publisher> findAll() {
        return jdbcTemplate.query("select id, name, short_name, contact_number, contact_name from manufacturer", new PublisherMapper());
    }

    public void update(Publisher publisher) {
        int update = jdbcTemplate.update("update manufacturer " +
                        "set name = ?, " +
                        "short_name = ?, " +
                        "contact_number = ?, " +
                        "contact_name = ? " +
                        "where id = ?",
                publisher.getName(),
                publisher.getShortName(),
                publisher.getContactNumber(),
                publisher.getContactName(),
                publisher.getId());

        if (update != 1)
            throw new RuntimeException("No publisher updated with id: " + publisher.getId());
    }

    static class PublisherMapper implements RowMapper<Publisher> {
        @Override
        public Publisher mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Publisher(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("short_name"),
                    rs.getString("contact_number"),
                    rs.getString("contact_name")
            );
        }
    }
}
