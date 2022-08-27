package com.doublegrooverecords.vinyl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Product> getAllPurchasedProducts(Long customerId) {
        return
                jdbcTemplate.query("select p.id, name as artist_name, album_title, image_location, price, cost, descrip from prod_artists pa\n" +
                        "join artist a on pa.artist_id = a.id\n" +
                        "join dg_product p on p.id = pa.product_id\n" +
                        "where p.id IN (" +
                        "select product_id from mrt_order o " +
                        "join mrt_order_line_items i on o.id = i.mrt_order_id " +
                        "where customer_code = ?);", new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                        String albumTitle = resultSet.getString("album_title");
                        Artist artistName = new Artist(resultSet.getString("artist_name"));
                        String imageLocation = resultSet.getString("image_location");
                        BigDecimal price = resultSet.getBigDecimal("price").movePointLeft(2);
                        BigDecimal cost = resultSet.getBigDecimal("cost").movePointLeft(2);
                        String description = resultSet.getString("descrip");
                        return new Product(resultSet.getLong("id"), albumTitle, artistName, imageLocation, price, cost, null, description);
                    }
                }, customerId);
    }
}
