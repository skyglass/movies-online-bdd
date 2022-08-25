package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/by-publisher")
public class ByPublisherPageController {
    PublisherRepository publisherRepository;
    ProductRepository productRepository;

    public ByPublisherPageController(PublisherRepository publisherRepository, ProductRepository productRepository) {
        this.publisherRepository = publisherRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public String allByPublisher(Model model) {
        model.addAttribute("publishers", publisherRepository.findAll());
        return "byPublisher";
    }

    @GetMapping("/{publisherId}")
    public String specificPublisher(@PathVariable Long publisherId, Model model) {
        model.addAttribute("publisher", publisherRepository.findForId(publisherId));
        model.addAttribute("products", productRepository.findByPublisherId(publisherId));
        return "publisher";
    }
}
