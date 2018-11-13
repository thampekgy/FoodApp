package com.example.windows10.newproject.Model;

import java.security.PrivateKey;

public class RestaurantOwner {

    private String ownerName;
    private String ownerGender;
    private String ownerContact;
    private String ownerEmail;
    private String password;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPostal;
    private String restaurantCity;
    private double restaurantRating;

    public RestaurantOwner(String ownerName, String ownerGender, String ownerContact, String ownerEmail, String password, String restaurantName, String restaurantAddress, String restaurantPostal, String restaurantCity, double restaurantRating) {
        this.ownerName = ownerName;
        this.ownerGender = ownerGender;
        this.ownerContact = ownerContact;
        this.ownerEmail = ownerEmail;
        this.password = password;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPostal = restaurantPostal;
        this.restaurantCity = restaurantCity;
        this.restaurantRating = restaurantRating;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerGender() {
        return ownerGender;
    }

    public void setOwnerGender(String ownerGender) {
        this.ownerGender = ownerGender;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantPostal() {
        return restaurantPostal;
    }

    public void setRestaurantPostal(String restaurantPostal) {
        this.restaurantPostal = restaurantPostal;
    }

    public String getRestaurantCity() {
        return restaurantCity;
    }

    public void setRestaurantCity(String restaurantCity) {
        this.restaurantCity = restaurantCity;
    }

    public double getRestaurantRating() {
        return restaurantRating;
    }

    public void setRestaurantRating(double restaurantRating) {
        this.restaurantRating = restaurantRating;
    }
}
