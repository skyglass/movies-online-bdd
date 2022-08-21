package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    ProductRepository productRepository;
    ProductGroupRepository flashSaleRepository;

    public HomeController(ProductRepository productRepository, ProductGroupRepository flashSaleRepository) {
        this.productRepository = productRepository;
        this.flashSaleRepository = flashSaleRepository;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("productGroups", flashSaleRepository.findById(1L));
        return "home";
    }
}
