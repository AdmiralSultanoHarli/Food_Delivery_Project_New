package com.example.fooddeliveryproject.Activities.Model;

public class DataYourFavourites {

    int id;
    String foodName;
    int img;

    public DataYourFavourites() {


    }

    public DataYourFavourites(int id, String foodName, int img) {
        this.id = id;
        this.foodName = foodName;
        this.img = img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getImg() {
        return img;
    }
}
