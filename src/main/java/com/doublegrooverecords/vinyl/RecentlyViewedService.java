package com.doublegrooverecords.vinyl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class RecentlyViewedService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductViewRepository productViewRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TimeWrapper timeWrapper;

    public List<Product> recentlyViewed(Long customerId) {
        final ZonedDateTime threeMonthsAgo = timeWrapper.now().minusMonths(3);
        final List<Order> orders = orderRepository.findBetweenDates(Date.from(threeMonthsAgo.toInstant()),
                Date.from(timeWrapper.now().plus(1, ChronoUnit.DAYS).toInstant())
        );

        final Set<Product> productsPurchasedInLastThreeMonths = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.toSet());

        return productViewRepository.mostRecentNViews(customerId, 5)
                .stream().map(productId -> productRepository.findById(productId))
                .distinct()
                .filter(product -> !productsPurchasedInLastThreeMonths.contains(product))
                .collect(Collectors.toList());
    }
}
