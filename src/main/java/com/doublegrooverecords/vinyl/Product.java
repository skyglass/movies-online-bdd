package com.doublegrooverecords.vinyl;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Product {
    public Product(Long id, String albumTitle, Artist artist, String imageUrl, BigDecimal price) {
        this.id = id;
        this.albumTitle = albumTitle;
        this.imageUrl = imageUrl;
        this.price = price;
        this.artists.add(artist);
    }

    final Long id;
    final String albumTitle;
    final List<Artist> artists = new ArrayList<>();
    final String imageUrl;
    private BigDecimal price;
}
