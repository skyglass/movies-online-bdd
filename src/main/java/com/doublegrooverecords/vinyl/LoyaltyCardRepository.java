package com.doublegrooverecords.vinyl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalUnit;
import java.util.Date;

@Repository
public class LoyaltyCardRepository {
    JdbcTemplate jdbcTemplate;

    public LoyaltyCardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    LoyaltyCard findOrCreateBy(Long customerId) {
        try {
            LoyaltyCard loyaltyCard = jdbcTemplate.queryForObject("select cust_code, purchase_count, expiry_epoch " +
                    "from mrt_cust_loyalty_card " +
                    "where cust_code = ?;", new RowMapper<LoyaltyCard>() {
                @Override
                public LoyaltyCard mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new LoyaltyCard(rs.getLong("cust_code"), rs.getInt("purchase_count"), rs.getDate("expiry_epoch"));
                }
            }, customerId);
            return loyaltyCard;
        } catch (EmptyResultDataAccessException e) {
            Date expiryEpoch = Date.from(ZonedDateTime.now().plus(10, ChronoUnit.MONTHS).toInstant());
            jdbcTemplate.update("insert into mrt_cust_loyalty_card " +
                    "(cust_code, purchase_count, expiry_epoch) " +
                    "values (?, ?, ?);", customerId, 0, expiryEpoch);

            return new LoyaltyCard(customerId, 0, expiryEpoch);
        }
    }

    public void save(LoyaltyCard lCard) {
        jdbcTemplate.update("update mrt_cust_loyalty_card " +
                        "set purchase_count = ? " +
                        ", expiry_epoch = ? " +
                        "where cust_code = ?;",
                lCard.getPurchaseCount(),
                lCard.getExpiryEpoch(),
                lCard.getCustomerId());
    }
}
