package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    ProductRepository productRepository;
    ProductGroupRepository flashSaleRepository;
    RecentlyViewedService recentlyViewedService;

    public HomeController(ProductRepository productRepository, ProductGroupRepository flashSaleRepository, RecentlyViewedService recentlyViewedService) {
        this.productRepository = productRepository;
        this.flashSaleRepository = flashSaleRepository;
        this.recentlyViewedService = recentlyViewedService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("productGroups", flashSaleRepository.findById(1L));
        model.addAttribute("recentlyLookedAt", recentlyViewedService.recentlyViewed(1L));
        return "home";
    }

}
