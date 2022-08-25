package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Component
public class LoyaltyCardDiscount {
    private LoyaltyCardRepository loyaltyCardRepository;

    public LoyaltyCardDiscount(LoyaltyCardRepository loyaltyCardRepository) {
        this.loyaltyCardRepository = loyaltyCardRepository;
    }

    public void apply(Long customerId, Calendar calendar, List<Product> list) {
        LoyaltyCard lCard = loyaltyCardRepository.findOrCreateBy(customerId);
        // is the card expired?
        if (lCard.getPurchaseCount() == 10) {
            // find the cheapest product and give it for free
            Product cheapest = list.get(0);
            for (Product p : list) {
                if (cheapest.getPrice().compareTo(p.getPrice()) > 0) {
                    cheapest = p;
                }
            }
            cheapest.setPrice(new BigDecimal(0)); // Free album

            // reset the count & epoch
            lCard.setPurchaseCount(0);
            calendar.add(Calendar.DAY_OF_YEAR, 300);
            lCard.setExpiryEpoch(calendar.getTime());
            loyaltyCardRepository.save(lCard);
        } else {
            // increase the number by one
            lCard.setPurchaseCount(lCard.getPurchaseCount() + 1);
            loyaltyCardRepository.save(lCard);
        }
    }
}
