package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesReportService {

    private OrderRepository orderRepository;

    public SalesReportService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public QuarterlySalesReport makeSalesReport(Quarter quarter) {
        List<Order> orders = orderRepository.findBetweenDates(quarter.getStart(), quarter.getEnd());
        Integer integer = orders.size();

        return new QuarterlySalesReport(integer, quarter.getName(), quarter.getStart(), quarter.getEnd(), orders);
    }
}
