package com.doublegrooverecords.vinyl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void create(Order order) {
        final int onlineStoreId = 122;
        Map<String, Object> customerData = new HashMap<>();
        customerData.put("customer_code", order.getCustomerId());
        customerData.put("created_on", new Date());
        customerData.put("store_id", onlineStoreId);
        customerData.put("street_address_1", order.getStreetAddress1());
        customerData.put("state_name", order.getStateName());
        customerData.put("city", order.getCity());
        customerData.put("zip", order.getZip());
        customerData.put("shipping_cost", order.getShippingCost().movePointRight(2));

        if (order.getStreetAddress2() != null) {
            customerData.put("street_address_2", order.getStreetAddress2());
        }

        Number orderId = new SimpleJdbcInsert(jdbcTemplate)
                .usingColumns("customer_code", "created_on", "store_id", "street_address_1", "street_address_2", "state_name", "city", "zip", "shipping_cost")
                .usingGeneratedKeyColumns("id")
                .withTableName("mrt_order")
                .executeAndReturnKey(
                        customerData);

        List<Object[]> lineItems = new ArrayList<>();


        for (Product p : order.getProducts()) {
            Object[] objects = new Object[]{orderId, p.getPrice().movePointRight(2), p.getId()};
            lineItems.add(objects);
        }

        jdbcTemplate.batchUpdate(
                "insert into mrt_order_line_items (mrt_order_id, charged_price,product_id) " +
                        "values (?, ?, ?)",
                lineItems);
    }

    public Order findByCustomerId(long customerId) {
        Map<Long, Order> orderIdToOrder = new HashMap<>();

        List<Order> orders = jdbcTemplate.query("select " +
                "o.id as order_id, " +
                "customer_code, " +
                "created_on, " +
                "store_id, " +
                "street_address_1, " +
                "street_address_2, " +
                "state_name, " +
                "city, " +
                "zip, " +
                "shipping_cost, " +
                "p.id as product_id, " +
                "p.album_title, " +
                "p.image_location, " +
                "li.charged_price, " +
                "sum(li.charged_price) over(partition by o.id) as total " +
                "from mrt_order o " +
                "join mrt_order_line_items li on li.mrt_order_id = o.id " +
                "join dg_product p on p.id = li.product_id " +
                "where o.customer_code = ? " +
                "order by created_on desc ", new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product p = new Product(rs.getLong("product_id"),
                        rs.getString("album_title"),
                        null,
                        rs.getString("image_location"),
                        rs.getBigDecimal("charged_price").movePointLeft(2));

                long orderId = rs.getLong("order_id");

                Order order = orderIdToOrder.get(orderId);

                if (order != null) {
                    order.getProducts().add(p);
                }
                else {
                    List<Product> products = new ArrayList<>();
                    products.add(p);
                    Order newOrder = new Order(orderId,
                            rs.getLong("customer_code"),
                            rs.getString("street_address_1"),
                            rs.getString("street_address_2"),
                            rs.getString("state_name"),
                            rs.getString("city"),
                            rs.getString("zip"),
                            rs.getBigDecimal("shipping_cost"),
                            rs.getBigDecimal("total").movePointLeft(2),
                            products);
                    orderIdToOrder.put(orderId, newOrder);
                }

                return null;
            }
        }, customerId);

        Collection<Order> values = orderIdToOrder.values();
        return new ArrayList<Order>(values).get(values.size() - 1);
    }
}
