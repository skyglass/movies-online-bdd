package com.doublegrooverecords.vinyl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {

    JdbcTemplate jdbc;

    public ProductRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Product> findAll() {
        return jdbc.query("select p.id, name as artist_name, album_title, image_location, price, cost, descrip from prod_artists pa\n" +
                        "join artist a on pa.artist_id = a.id\n" +
                        "join dg_product p on p.id = pa.product_id;",
                new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Product(rs.getLong("id"), rs.getString("album_title"), new Artist(rs.getString("artist_name")), rs.getString("image_location"), rs.getBigDecimal("price").movePointLeft(2), rs.getBigDecimal("cost").movePointLeft(2), null, null);
                    }
                });
    }

    public List<Product> findByName(String term) {
        return jdbc.query("select p.id, name as artist_name, album_title, image_location, price, cost, descrip from \n" +
                        "(select id, name from artist a where name like ? ) m\n" +
                        "join prod_artists pa on m.id = pa.artist_id\n" +
                        "join dg_product p on pa.product_id = p.id;",
                new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Product(rs.getLong("id"), rs.getString("album_title"), new Artist(rs.getString("artist_name")), rs.getString("image_location"), rs.getBigDecimal("price").movePointLeft(2), rs.getBigDecimal("cost").movePointLeft(2), null, null);
                    }
                }, String.format("%%%s%%", term));
    }

    public List<Product> findByPublisherId(Long publisherId) {
        return jdbc.query("select p.id, name as artist_name, album_title, image_location, price, cost, descrip from prod_artists pa\n" +
                        "join artist a on pa.artist_id = a.id\n" +
                        "join dg_product p on p.id = pa.product_id " +
                        "where p.m_id = ?",
                new ProductRowMapper(), publisherId);
    }

    private BigDecimal formatPrice(BigDecimal bigDecimal) throws SQLException {
        return bigDecimal.movePointLeft(2);
    }

    private Product createProduct(long id, String albumTitle, Artist artistName, String imageLocation, BigDecimal price, BigDecimal cost, String description) {
        return new Product(id, albumTitle, artistName, imageLocation, price, cost, null, description);
    }

    public Product findById(Long id) {
        return jdbc.queryForObject("select p.id, name as artist_name, album_title, image_location, price, cost, descrip from prod_artists pa\n" +
                "join artist a on pa.artist_id = a.id\n" +
                "join dg_product p on p.id = pa.product_id\n" +
                "where p.id = ?;", new ProductRowMapper(), id);
    }

    class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String albumTitle = rs.getString("album_title");
            Artist artistName = new Artist(rs.getString("artist_name"));
            String imageLocation = rs.getString("image_location");
            BigDecimal price = formatPrice(rs.getBigDecimal("price"));
            BigDecimal cost = formatPrice(rs.getBigDecimal("cost"));
            String description = rs.getString("descrip");
            return createProduct(id, albumTitle, artistName, imageLocation, price, cost, description);
        }
    }
}
