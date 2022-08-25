package com.doublegrooverecords.vinyl;

import org.springframework.data.domain.Range;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/cart")
public class CartController {
    private CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    long customerId = 1L;

    @GetMapping
    public String cart(Model model) {
        model.addAttribute("cart", cartRepository.find(customerId));
        return "cart";
    }

    @PostMapping("/add")
    public String cart(Long product, Long quantity) {
        IntStream.iterate(0, i -> i < quantity, i -> i + 1).forEach(i -> cartRepository.addToCart(customerId, product));
        return "redirect:/cart";
    }

    @PostMapping("/coupon")
    public String coupon(Model model, String coupon) {
        try {
            cartRepository.addCoupon(customerId, coupon);
        } catch (Exception e) {

        }
        model.addAttribute("cart", cartRepository.find(customerId));
        return "cart";
    }
}
