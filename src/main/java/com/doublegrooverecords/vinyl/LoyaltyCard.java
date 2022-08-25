package com.doublegrooverecords.vinyl;

import lombok.Data;

import java.util.Date;

@Data
public class LoyaltyCard {
    private int purchaseCount;
    private long customerId;
    private Date expiryEpoch;

    public LoyaltyCard(long customerId, int purchaseCount, Date expiryEpoch) {
        this.customerId = customerId;
        this.purchaseCount = purchaseCount;
        this.expiryEpoch = expiryEpoch;
    }
}
