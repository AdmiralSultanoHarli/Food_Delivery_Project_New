package com.example.fooddeliveryproject.Activities.MenuScreenItem;

public class DataFoodMenu {

    String foodName;
    String foodDescription;
    String foodPrice;
    String foodPriceDiscount;
    int img;

    public DataFoodMenu() {

    }

    public DataFoodMenu(String foodName, String foodDescription, String foodPrice, String foodPriceDiscount, int img) {
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.foodPriceDiscount = foodPriceDiscount;
        this.img = img;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public String getFoodPriceDiscount() {
        return foodPriceDiscount;
    }

    public int getImg() {
        return img;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setFoodPriceDiscount(String foodPriceDiscount) {
        this.foodPriceDiscount = foodPriceDiscount;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
