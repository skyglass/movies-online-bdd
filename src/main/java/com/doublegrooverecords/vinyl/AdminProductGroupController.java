package com.doublegrooverecords.vinyl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

@RequestMapping("/admin")
@Controller
public class AdminProductGroupController {

    ProductGroupRepository productGroupRepository;

    public AdminProductGroupController(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("productGroupId", productGroupRepository.first().getId());
        return "admin";
    }

    @GetMapping("/group/{id}/edit")
    public String productGroups(Model model, @PathVariable("id") Long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        model.addAttribute("productGroups", objectMapper.writeValueAsString(productGroupRepository.findById(id)));
        return "product_groups";
    }

    @PostMapping("/group/{id}")
    public String updateProductGroups(ProductGroup productGroup, Model model, @PathVariable("id") Long id) throws JsonProcessingException {
        productGroupRepository.update(productGroup);
        ObjectMapper objectMapper = new ObjectMapper();
        model.addAttribute("productGroups", objectMapper.writeValueAsString(productGroupRepository.findById(id)));
        return "product_groups";
    }
}
