package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loyalty")
public class LoyaltyCardController {
    LoyaltyCardRepository loyaltyCardRepository;

    public LoyaltyCardController(LoyaltyCardRepository loyaltyCardRepository) {
        this.loyaltyCardRepository = loyaltyCardRepository;
    }

    @GetMapping()
    public String loyaltyCard(Model model) {
        model.addAttribute("loyaltyCard", loyaltyCardRepository.findOrCreateBy(1L));
        return "card";
    }
}
