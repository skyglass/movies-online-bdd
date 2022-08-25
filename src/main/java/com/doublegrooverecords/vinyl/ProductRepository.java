package com.doublegrooverecords.vinyl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    JdbcTemplate jdbc;

    public ProductRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Product> findAll() {
        return jdbc.query("select p.id, name as artist_name, album_title, image_location, price from prod_artists pa\n" +
                        "join artist a on pa.artist_id = a.id\n" +
                        "join dg_product p on p.id = pa.product_id;",
                new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Product(rs.getLong("id"), rs.getString("album_title"), new Artist(rs.getString("artist_name")), rs.getString("image_location"), rs.getBigDecimal("price").movePointLeft(2));
                    }
                });
    }

    public List<Product> findByName(String term) {
        return jdbc.query("select p.id, name as artist_name, album_title, image_location, price from \n" +
                        "(select id, name from artist a where name like ? ) m\n" +
                        "join prod_artists pa on m.id = pa.artist_id\n" +
                        "join dg_product p on pa.product_id = p.id;",
                new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Product(rs.getLong("id"), rs.getString("album_title"), new Artist(rs.getString("artist_name")), rs.getString("image_location"), rs.getBigDecimal("price").movePointLeft(2));
                    }
                }, String.format("%%%s%%", term));
    }
}
