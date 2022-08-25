package com.doublegrooverecords.vinyl;

import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ComparedSalesReport {
    private final QuarterlySalesReport currentlyQuarterlyReport;
    private final QuarterlySalesReport previousQuarter;

    public ComparedSalesReport(QuarterlySalesReport currentQuarterlyReport, QuarterlySalesReport previousQuarter) {
        this.currentlyQuarterlyReport = currentQuarterlyReport;
        this.previousQuarter = previousQuarter;
    }

    public Integer getTotalSold() {
        return currentlyQuarterlyReport.getTotalSold();
    }

    public String getQuarter() {
        return currentlyQuarterlyReport.getQuarter();
    }

    public BigDecimal getTotal() {
        return currentlyQuarterlyReport.getTotal();
    }

    public BigDecimal getProfit() {
        return currentlyQuarterlyReport.getProfit();
    }

    public List<ComparedProduct> getMostPurchased() {
        final List<Product> previousMostPurchasedProducts = previousQuarter.getMostPurchased();

        final List<Product> currentMostPurchasedProducts = currentlyQuarterlyReport.getMostPurchased();

        return currentMostPurchasedProducts
                .stream()
                .map(product -> {
                    final ComparedPosition position = determinePosition(previousMostPurchasedProducts, currentMostPurchasedProducts, product);
                    return new ComparedProduct(product, position);
                })
                .collect(Collectors.toList());
    }

    private ComparedPosition determinePosition(List<Product> previousMostPurchasedProducts, List<Product> currentMostPurchasedProducts, Product product) {
        final int previousIndex = previousMostPurchasedProducts.indexOf(product);
        final int currentIndex = currentMostPurchasedProducts.indexOf(product);

        if (previousIndex == -1)
            return ComparedPosition.NewToList;

        if (previousIndex == currentIndex) {
            return ComparedPosition.NoChange;
        } else if (previousIndex > currentIndex) {
            return ComparedPosition.Increased;
        }
        return ComparedPosition.Decreased;
    }

    public BigDecimal getCost() {
        return currentlyQuarterlyReport.getCost();
    }

    public List<Pair<Long, BigDecimal>> getAmountOwedToManufacturers() {
        return currentlyQuarterlyReport.getAmountOwedToManufacturers();
    }
}
