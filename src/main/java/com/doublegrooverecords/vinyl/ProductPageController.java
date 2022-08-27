package com.doublegrooverecords.vinyl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class ProductPageController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductPageService productPageService;
    private long customerId = 1L;

    @Value("${features.wishlist}")
    boolean wishlistEnabled;

    @GetMapping("/product/{id}/{albumSlug}/")
    public String productPage(@PathVariable Long id, @PathVariable String albumSlug, Model model, HttpServletRequest request) {
        Product product = productRepository.findById(id);
        
        final String slug = getAlbumTitleSlug(product);
        if (!slug.equals(albumSlug)) {
            return String.format("redirect:/product/%s/%s/", product.getId(), slug);
        }
        model.addAttribute("product", product);

        final ProductPage productPage = productPageService.execute(id, customerId);
        model.addAttribute("recProducts", productPage.getRecommended());

        model.addAttribute("wishlistEnabled", wishlistEnabled);

        return "product";
    }

    private String getAlbumTitleSlug(Product product) {
        return product.albumTitle.replace(" ", "-").toLowerCase(Locale.ROOT);
    }
}
