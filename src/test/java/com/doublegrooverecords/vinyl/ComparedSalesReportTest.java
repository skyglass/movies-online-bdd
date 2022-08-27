package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ComparedSalesReportTest {
    @Test
    void getMostPurchased_returnsEmptyList_whenBothSaleReportsAreEmpty() {
        QuarterlySalesReport currentQuarter = mock(QuarterlySalesReport.class);
        when(currentQuarter.getMostPurchased()).thenReturn(List.of());

        QuarterlySalesReport previousQuarter = mock(QuarterlySalesReport.class);
        when(previousQuarter.getMostPurchased()).thenReturn(List.of());

        final ComparedSalesReport comparedSalesReport = new ComparedSalesReport(currentQuarter, previousQuarter);

        assertThat(comparedSalesReport.getMostPurchased()).hasSize(0);
    }

    @Test
    void getMostPurchased_returnsNewToListPosition_whenPreviousQuarterHadNoProducts() {
        final Product productOne = new Product(1L, null, null, null, null, null, null, null);

        QuarterlySalesReport currentQuarter = mock(QuarterlySalesReport.class);
        when(currentQuarter.getMostPurchased()).thenReturn(List.of(productOne));

        QuarterlySalesReport previousQuarter = mock(QuarterlySalesReport.class);
        when(previousQuarter.getMostPurchased()).thenReturn(List.of());

        final ComparedSalesReport comparedSalesReport = new ComparedSalesReport(currentQuarter, previousQuarter);

        assertThat(comparedSalesReport.getMostPurchased())
                .hasSize(1)
                .first()
                .extracting(ComparedProduct::getPosition)
                .isEqualTo(ComparedPosition.NewToList);
    }

    @Test
    void getMostPurchased_returnsNoChange_whenProductIsInSamePosition() {
        final Product productOne = new Product(1L, null, null, null, null, null, null, null);

        final Product differentInstanceProductOne = new Product(1L, null, null, null, null, null, null, null);

        QuarterlySalesReport currentQuarter = mock(QuarterlySalesReport.class);
        when(currentQuarter.getMostPurchased()).thenReturn(List.of(productOne));

        QuarterlySalesReport previousQuarter = mock(QuarterlySalesReport.class);
        when(previousQuarter.getMostPurchased()).thenReturn(List.of(differentInstanceProductOne));

        final ComparedSalesReport comparedSalesReport = new ComparedSalesReport(currentQuarter, previousQuarter);

        assertThat(comparedSalesReport.getMostPurchased())
                .hasSize(1)
                .first()
                .extracting(ComparedProduct::getPosition)
                .isEqualTo(ComparedPosition.NoChange);
    }

    @Test
    void getMostPurchased_returnsIncreased_whenProductMovesUpList() {
        final Product productOne = new Product(1L, null, null, null, null, null, null, null);
        final Product productTwo = new Product(2L, null, null, null, null, null, null, null);

        QuarterlySalesReport currentQuarter = mock(QuarterlySalesReport.class);
        when(currentQuarter.getMostPurchased()).thenReturn(List.of(productOne, productTwo));

        QuarterlySalesReport previousQuarter = mock(QuarterlySalesReport.class);
        when(previousQuarter.getMostPurchased()).thenReturn(List.of(productTwo, productOne));

        final ComparedSalesReport comparedSalesReport = new ComparedSalesReport(currentQuarter, previousQuarter);

        assertThat(comparedSalesReport.getMostPurchased())
                .hasSize(2)
                .first()
                .extracting(ComparedProduct::getPosition)
                .isEqualTo(ComparedPosition.Increased);
    }

    @Test
    void getMostPurchased_returnsDecreased_whenProductMovesDownList() {
        final Product productOne = new Product(1L, null, null, null, null, null, null, null);
        final Product productTwo = new Product(2L, null, null, null, null, null, null, null);

        QuarterlySalesReport currentQuarter = mock(QuarterlySalesReport.class);
        when(currentQuarter.getMostPurchased()).thenReturn(List.of(productOne, productTwo));

        QuarterlySalesReport previousQuarter = mock(QuarterlySalesReport.class);
        when(previousQuarter.getMostPurchased()).thenReturn(List.of(productTwo, productOne));

        final ComparedSalesReport comparedSalesReport = new ComparedSalesReport(currentQuarter, previousQuarter);

        assertThat(comparedSalesReport.getMostPurchased())
                .hasSize(2)
                .last()
                .extracting(ComparedProduct::getPosition)
                .isEqualTo(ComparedPosition.Decreased);
    }
}