package com.doublegrooverecords.vinyl;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin/sales")
@Controller
public class AdminSalesController {
    SalesReportService salesReportService;
    PublisherRepository publisherRepository;

    public AdminSalesController(SalesReportService salesReportService, PublisherRepository publisherRepository) {
        this.salesReportService = salesReportService;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping
    public String salesPanel(Model model) {
        final Quarter currentQuarter = QuarterFactory.make(Clock.systemUTC());
        final QuarterlySalesReport currentQuarterlyReport = salesReportService.makeSalesReport(currentQuarter);
        final QuarterlySalesReport previousQuarterlyReport = salesReportService.makeSalesReport(currentQuarter.previous());
        ComparedSalesReport salesReport = new ComparedSalesReport(currentQuarterlyReport, previousQuarterlyReport);
        model.addAttribute("totalSold", salesReport.getTotalSold());
        model.addAttribute("currentQuarter", salesReport.getQuarter());
        model.addAttribute("totalRevenue", salesReport.getTotal());
        model.addAttribute("profit", salesReport.getProfit());
        model.addAttribute("cost", salesReport.getCost());
        model.addAttribute("mostPurchased", salesReport.getMostPurchased());
        List<Pair<Long, BigDecimal>> amountOwedToManufacturers = salesReport.getAmountOwedToManufacturers();

        List<Pair<Publisher, BigDecimal>> publisherToAmountOwed = new ArrayList<>();
        for (Pair<Long, BigDecimal> p : amountOwedToManufacturers) {
            publisherToAmountOwed.add(Pair.of(publisherRepository.findForId(p.getFirst()), p.getSecond()));
        }

        model.addAttribute("publishers", publisherToAmountOwed);
        return "sales";
    }
}
