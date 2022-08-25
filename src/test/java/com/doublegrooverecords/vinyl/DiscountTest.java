package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiscountTest {
    @Test
    public void returnsOriginalAmount_whenDiscountIsNone() {
        final BigDecimal amount = new BigDecimal("100.00");

        Discount none = Discount.NONE;

        assertThat(none.apply(amount)).isEqualTo(new BigDecimal("100.00"));
    }

    @Test
    public void returnsDiscountedAmount_whenDiscountIsTenPercent() {
        final BigDecimal amount = new BigDecimal("100.00");

        Discount tenPercent = Discount.TEN_PERCENT;

        assertThat(tenPercent.apply(amount)).isEqualTo(new BigDecimal("90.00"));
    }

    @Test
    public void returnsRoundedUpAmount_whenAmountHasCentsAndDiscountIsTenPercent() {
        final BigDecimal amount = new BigDecimal("34.21");

        Discount tenPercent = Discount.TEN_PERCENT;

        assertThat(tenPercent.apply(amount)).isEqualTo(new BigDecimal("30.79"));
    }
}