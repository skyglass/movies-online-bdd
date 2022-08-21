package com.doublegrooverecords.api;

import com.doublegrooverecords.vinyl.Artist;
import com.doublegrooverecords.vinyl.Product;
import com.doublegrooverecords.vinyl.ProductRepository;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class SearchRestController {
    ProductRepository productRepository;

    public SearchRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/api/search", produces = "application/json")
    public List<Product> search(@RequestParam String term) {
        return productRepository.findByName(term);
    }
}
