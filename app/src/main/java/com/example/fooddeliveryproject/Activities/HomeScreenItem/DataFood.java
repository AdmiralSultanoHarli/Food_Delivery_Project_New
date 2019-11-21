package com.example.fooddeliveryproject.Activities.HomeScreenItem;

public class DataFood {

    String foodName;
    int img;

    public DataFood() {


    }

    public DataFood(String foodName, int img) {
        this.foodName = foodName;
        this.img = img;
    }

    public String getFoodName()  {
        return foodName;
    }

    public int getImg() {
        return img;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
