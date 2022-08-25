package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    CartPurchasingService cartPurchasingService;
    private final long customerId = 1L;
    private OrderRepository orderRepository;

    public OrderController(CartPurchasingService cartPurchasingService, OrderRepository orderRepository) {
        this.cartPurchasingService = cartPurchasingService;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/purchase")
    public String checkout() {
        cartPurchasingService.purchaseCart(customerId);
        return "redirect:/order/success";
    }

    @GetMapping("/success")
    public String success(Model model) {
        model.addAttribute("order", orderRepository.findByCustomerId(customerId));

        return "success";
    }
}
