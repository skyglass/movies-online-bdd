package com.doublegrooverecords.vinyl;

public class UIProduct {
  private Long id;
  private final String album;
  private final String artist;
  private final String imageUrl;

  public UIProduct(Long id, String album, String artist, String imageUrl) {
    this.id = id;
    this.artist = artist;
    this.album = album;
    this.imageUrl = imageUrl;
  }

  public UIProduct(String album, String artist, String imageUrl) {
    this.album = album;
    this.artist = artist;
    this.imageUrl = imageUrl;
  }

  public String getAlbum() {
    return album;
  }

  public String getArtist() {
    return artist;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public Long getId() {
    return id;
  }
}
