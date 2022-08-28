package com.doublegrooverecords.vinyl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductViewRepository {

  @Autowired
  JdbcTemplate jdbc;

  public void logViewFor(Long productId, long customerId) {
    jdbc.update("merge into customer_view_dg_product (customer_id, product_id, last_viewed_on) " +
            "values (?, ?, NOW());", customerId, productId);
  }

  public List<Long> mostRecentNViews(Long customerId, int n) {
    return jdbc.query("select product_id from customer_view_dg_product " +
            "where customer_id = ? " +
            "order by last_viewed_on desc " +
            "limit ?", (rs, rowNum) -> rs.getLong("product_id"), customerId, n);
  }
}
