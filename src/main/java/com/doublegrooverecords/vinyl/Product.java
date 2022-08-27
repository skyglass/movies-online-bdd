package com.doublegrooverecords.vinyl;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Product {
    public Product(Long id, String albumTitle, Artist artist, String imageUrl, BigDecimal price, BigDecimal cost, Long publisherId, String description) {
        this.id = id;
        this.albumTitle = albumTitle;
        this.imageUrl = imageUrl;
        this.price = price;
        this.cost = cost;
        this.description = description;
        this.artists.add(artist);
        this.publisherId = publisherId;
    }

    final Long id;
    final Long publisherId;
    final String albumTitle;
    final List<Artist> artists = new ArrayList<>();
    final String imageUrl;
    private BigDecimal price;
    private BigDecimal cost;
    private String description;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if ((Product)o == null) {
            return false;
        }
        Product other = (Product)o;
        return this.getId().equals(other.id);
    }
}
