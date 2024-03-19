package com.example.discoraterjorge;

public class Post {

    private String name, location, experience;
    private long timestamp;
    private String cocktailPrice;
    private String beerPrice;
    private String tequilaPrice;
    private String imageUri;

    // No-argument constructor
    public Post() {
    }

    // Argument-based constructor
    public Post(String name, String location, long timestamp, String experience, String cocktailPrice, String beerPrice, String tequilaPrice, String imageUri) {
        this.name = name;
        this.location = location;
        this.timestamp = timestamp;
        this.experience = experience;
        this.cocktailPrice = cocktailPrice;
        this.beerPrice = beerPrice;
        this.tequilaPrice = tequilaPrice;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCocktailPrice() {
        return cocktailPrice;
    }

    public void setCocktailPrice(String cocktailPrice) {
        this.cocktailPrice = cocktailPrice;
    }

    public String getBeerPrice() {
        return beerPrice;
    }

    public void setBeerPrice(String beerPrice) {
        this.beerPrice = beerPrice;
    }

    public String getTequilaPrice() {
        return tequilaPrice;
    }

    public void setTequilaPrice(String tequilaPrice) {
        this.tequilaPrice = tequilaPrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getImageUri() {return imageUri; }

    public void setImageUri(String imageUri) {this.imageUri = imageUri; }

}
