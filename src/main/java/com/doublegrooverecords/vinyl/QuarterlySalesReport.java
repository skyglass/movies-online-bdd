package com.doublegrooverecords.vinyl;

import lombok.Data;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class QuarterlySalesReport {
    Integer totalSold;
    String quarter;
    Date start;
    Date end;
    List<Order> orders;
    BigDecimal profit;
    BigDecimal cost;

    public QuarterlySalesReport(Integer totalSold, String quarter, Date start, Date end, List<Order> orders) {
        this.totalSold = totalSold;
        this.quarter = quarter;
        this.start = start;
        this.end = end;
        this.orders = orders;
    }

    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(0);
        for (Order order : orders) {
            total = total.add(order.getTotal());
        }
        return total;
    }

    public BigDecimal getCost() {
        BigDecimal cost = new BigDecimal(0);
        ;
        for (Order order : orders) {
            BigDecimal result = totalForOrder(order);
            cost = cost.add(result);
        }
        return cost;
    }

    private BigDecimal totalForOrder(Order order) {
        List<Product> pl = order.getProducts();
        BigDecimal result = new BigDecimal(0);
        for (Product product : pl) {
            BigDecimal productCost = product.getCost();
            result = result.add(productCost);
        }
        return result;
    }

    public BigDecimal getProfit() {
        return getTotal().subtract(getCost());
    }

    public List<Pair<Long, BigDecimal>> getAmountOwedToManufacturers() {
        ArrayList<Pair<Long, BigDecimal>> pairs = new ArrayList<>();

        for (Order o : orders) {
            for (Product aProduct : o.getProducts()) {
                int indexOfProduct = findIndexOf(pairs, aProduct);
                if (indexOfProduct != -1) {
                    Pair<Long, BigDecimal> aPair = pairs.get(indexOfProduct);
                    pairs.remove(indexOfProduct);
                    pairs.add(indexOfProduct, Pair.of(aProduct.getPublisherId(), aPair.getSecond().add(aProduct.getCost())));
                } else {
                    pairs.add(Pair.of(aProduct.getPublisherId(), aProduct.getCost()));
                }
            }
        }

        return pairs;
    }

    private int findIndexOf(ArrayList<Pair<Long, BigDecimal>> pairs, Product aProduct) {
        Iterator<Pair<Long, BigDecimal>> iterator = pairs.iterator();
        int c = 0;
        while (iterator.hasNext()) {
            Pair<Long, BigDecimal> pair = iterator.next();
            if (pair.getFirst() == aProduct.getPublisherId()) {
                return c;
            }

            c++;
        }

        return -1;
    }

    public List<Product> getMostPurchased() {
        Map<Product, Integer> productCount = new HashMap<>();

        orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .forEach(p -> {
                    if (productCount.containsKey(p)) {
                        productCount.put(p, productCount.get(p) + 1);
                    } else {
                        productCount.put(p, 1);
                    }
                });

        return sortProducts(productCount);
    }

    private List<Product> sortProducts(Map<Product, Integer> productCount) {
        return productCount.keySet()
                .stream()
                .sorted((left, right) -> productCount.get(right).compareTo(productCount.get(left)))
                .collect(Collectors.toList());
    }
}
