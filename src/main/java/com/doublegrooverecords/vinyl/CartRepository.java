package com.doublegrooverecords.vinyl;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CartRepository {
    private JdbcTemplate jdbcTemplate;

    public CartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void addToCart(Long customerId, Long productId) {
        insertCartIfNotExists(customerId);
        
        jdbcTemplate.update("insert into cart_products (cart_id, product_id) values ((select id from cart where customer_code = ?), ?)", customerId, productId);
    }

    private void insertCartIfNotExists(Long customerId) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        final String insertCartWhenNotExists = "insert into cart (customer_code) " +
                "select :id from dual " +
                "where not exists (select * from cart where customer_code = :id)";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", customerId);

        namedParameterJdbcTemplate.update(insertCartWhenNotExists, parameters);
    }

    public void clear(Long customerId) {
        jdbcTemplate.update("delete from cart where customer_code = ?", customerId);
    }

    public Cart find(Long customerId) {
        insertCartIfNotExists(customerId);

        List<CartProduct> products = jdbcTemplate.query("select count(*) as qty, p.id, name as artist_name, album_title, image_location, price from prod_artists pa " +
                        "join artist a on pa.artist_id = a.id " +
                        "join dg_product p on p.id = pa.product_id " +
                        "join cart_products cp on cp.product_id = p.id " +
                        "join cart c on c.id = cp.cart_id " +
                        "where c.customer_code = ? " +
                        "group by p.id;",
                new RowMapper<CartProduct>() {
                    @Override
                    public CartProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                        CartProduct product = new CartProduct(rs.getLong("id"), rs.getString("album_title"), new Artist(rs.getString("artist_name")), rs.getString("image_location"), rs.getBigDecimal("price").movePointLeft(2));
                        product.setQuantity(rs.getLong("qty"));
                        return product;
                    }
                }, customerId);

        Boolean cartWithDiscount = jdbcTemplate.queryForObject("select coupon_id from cart where customer_code = ?", new RowMapper<Boolean>() {
            @Override
            public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong("coupon_id") != 0;
            }
        }, customerId);
        Discount discount = cartWithDiscount ? Discount.TEN_PERCENT : Discount.NONE;

        return new Cart(products, discount);
    }

    public void addCoupon(long customerId, String coupon) throws Exception {
        try {
            insertCartIfNotExists(customerId);
        } catch (Exception e) {
            throw new CustomerNotFoundException();
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        namedParameterJdbcTemplate.update("update cart " +
                "set coupon_id = (select id from coupon_code where code = :coupon) " +
                "where customer_code = :id " +
                "and exists (select * from coupon_code where code = :coupon)", Map.of("coupon", coupon, "id", customerId));
    }
}
