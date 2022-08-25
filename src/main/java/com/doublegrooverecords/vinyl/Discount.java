package com.doublegrooverecords.vinyl;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Discount {
    NONE(0),
    TEN_PERCENT(10);

    private int amount;

    Discount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal apply(BigDecimal value) {
        if (this == Discount.NONE)
            return value;
        else
            return value.subtract(value.divide(new BigDecimal("10"))).setScale(2, RoundingMode.CEILING);
    }
}
