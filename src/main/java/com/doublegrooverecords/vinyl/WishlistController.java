package com.doublegrooverecords.vinyl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@ConditionalOnProperty(name = "features.wishlist")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;

    @Autowired
    ProductRepository productRepository;

    private long customerId = 1L;

    @PostMapping("/wishlist/add")
    public String addToWishlist(Long product) {
        wishlistService.addProductToWishlist(customerId, productRepository.findById(product));
        return "redirect:/wishlist";
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model) {
        model.addAttribute("wishlist", wishlistService.getAllProductsForUser(customerId));
        return "wishlist";
    }
}
