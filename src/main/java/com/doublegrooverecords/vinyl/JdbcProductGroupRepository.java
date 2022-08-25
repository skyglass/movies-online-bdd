package com.doublegrooverecords.vinyl;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcProductGroupRepository implements ProductGroupRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcProductGroupRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void update(ProductGroup productGroup) {
        jdbcTemplate.update("delete from product_group_products where product_group_id = ?", productGroup.getId());
        jdbcTemplate.update("update product_group pg set pg.name = ? where id = ?", productGroup.getName(), productGroup.getId());

        jdbcTemplate.batchUpdate(
                "insert into product_group_products (product_group_id, product_id) values (?, ?);",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, productGroup.getId());
                        ps.setLong(2, productGroup.items.get(i));
                    }

                    @Override
                    public int getBatchSize() {
                        return productGroup.items.size();
                    }
                });
    }

    @Override
    @Transactional
    public ProductGroup findById(Long id) {
        List<Product> products = jdbcTemplate.query("select p.id, a.name as artist_name, album_title, image_location, price from product_group pg " +
                        "join product_group_products pgp on pgp.product_group_id = pg.id " +
                        "join dg_product p on pgp.product_id = p.id " +
                        "join prod_artists pa on p.id = pa.product_id " +
                        "join artist a on pa.artist_id = a.id " +
                        "where pg.id = ?;", new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Product(
                                rs.getLong("id"),
                                rs.getString("album_title"),
                                new Artist(rs.getString("artist_name")),
                                rs.getString("image_location"),
                                rs.getBigDecimal("price").movePointLeft(2),
                                null, null
                        );
                    }
                }, id
        );

        Long productGroupId = 1L;
        return jdbcTemplate.queryForObject("select pg.name as name from product_group pg where id = ?", new RowMapper<ProductGroup>() {
            @Override
            public ProductGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ProductGroup(productGroupId, products, rs.getString("name"));
            }
        }, productGroupId);
    }

    @Override
    public ProductGroup first() {
        List<Product> products = jdbcTemplate.query("select p.id, a.name as artist_name, album_title, image_location, price from product_group pg " +
                        "join product_group_products pgp on pgp.product_group_id = pg.id " +
                        "join dg_product p on pgp.product_id = p.id " +
                        "join prod_artists pa on p.id = pa.product_id " +
                        "join artist a on pa.artist_id = a.id " +
                        "where pg.id = (select id from product_group limit 1);", new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Product(
                                rs.getLong("id"),
                                rs.getString("album_title"),
                                new Artist(rs.getString("artist_name")),
                                rs.getString("image_location"),
                                rs.getBigDecimal("price"),
                                null, null
                        );
                    }
                }
        );

        Long productGroupId = 1L;
        return jdbcTemplate.queryForObject("select pg.name as name from product_group pg where id = ?", new RowMapper<ProductGroup>() {
            @Override
            public ProductGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ProductGroup(productGroupId, products, rs.getString("name"));
            }
        }, productGroupId);
    }
}
