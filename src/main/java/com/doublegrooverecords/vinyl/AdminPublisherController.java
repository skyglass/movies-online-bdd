package com.doublegrooverecords.vinyl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminPublisherController {
    @Autowired
    PublisherRepository publisherRepository;

    @GetMapping("/admin/publisher")
    public String publishers(Model model) {
        model.addAttribute("publishers", publisherRepository.findAll());

        return "all_publishers";
    }

    @GetMapping("/admin/publisher/{id}")
    public String publisherDetails(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherRepository.findForId(id));
        return "publisher_details";
    }

    @GetMapping("/admin/publisher/{id}/edit")
    public String editPublisher(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherRepository.findForId(id));
        return "edit_publisher";
    }

    @PostMapping("/admin/publisher/{id}")
    public String updatePublisher(@PathVariable Long id, Publisher publisher) {
        publisherRepository.update(publisher);
        return "redirect:/admin/publisher/" + id;
    }
}
