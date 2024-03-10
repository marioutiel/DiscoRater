package com.example.discoraterjorge;

public class Disco {

    private String name, day, events, theme;
    private String cocktailPrice;
    private String beerPrice;
    private String tequilaPrice;
    private String imageUri;

    public Disco(String name, String day, String theme, String events, String cocktailPrice, String beerPrice, String tequilaPrice, String imageUri) {
        this.name = name;
        this.day = day;
        this.theme = theme;
        this.events = events;
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

    public String getEvents() {
        return events;
    }
    public void setEvents(String events) {
        this.events = events;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getImageUri() {return imageUri; }

    public void setImageUri(String imageUri) {this.imageUri = imageUri; }

}
