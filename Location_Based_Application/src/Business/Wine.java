/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author raywa
 */
public class Wine {
    private String wineName;
    private double alcoholPercentage;
    private double price;
    private String originCountry;
    private String location;
    private String grape;
    private int numberofInventory;
    private Seller seller;

    public Wine() {
    }

    @Override
    public String toString() {
        return wineName;
    }

    public String getWineName() {
        return wineName;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGrape() {
        return grape;
    }

    public void setGrape(String grape) {
        this.grape = grape;
    }

    public int getNumberofInventory() {
        return numberofInventory;
    }

    public void setNumberofInventory(int numberofInventory) {
        this.numberofInventory = numberofInventory;
    }
    
}
