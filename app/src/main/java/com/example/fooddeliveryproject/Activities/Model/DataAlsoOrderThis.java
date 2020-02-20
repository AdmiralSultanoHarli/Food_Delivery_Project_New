package com.example.fooddeliveryproject.Activities.Model;

public class DataAlsoOrderThis {

    int foodId;
    String foodName;
    String foodDesc;
    int foodPrice;
    int foodPriceDiscount;
    int foodPriceTotal;
    int foodPriceDiscountTotal;
    int buttonPosition;
    int foodItemCount;
    int foodFavourites;
    int foodImg;

    public DataAlsoOrderThis() {



    }

    public DataAlsoOrderThis(int foodId, String foodName, String foodDesc, int foodPrice, int foodPriceDiscount, int foodPriceTotal,
                             int foodPriceDiscountTotal, int buttonPosition, int foodItemCount, int foodFavourites, int foodImg) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.foodPrice = foodPrice;
        this.foodPriceDiscount = foodPriceDiscount;
        this.foodPriceTotal = foodPriceTotal;
        this.foodPriceDiscountTotal = foodPriceDiscountTotal;
        this.buttonPosition = buttonPosition;
        this.foodItemCount = foodItemCount;
        this.foodFavourites = foodFavourites;
        this.foodImg = foodImg;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setFoodPriceDiscount(int foodPriceDiscount) {
        this.foodPriceDiscount = foodPriceDiscount;
    }

    public void setFoodPriceTotal(int foodPriceTotal) {
        this.foodPriceTotal = foodPriceTotal;
    }

    public void setFoodPriceDiscountTotal(int foodPriceDiscountTotal) {
        this.foodPriceDiscountTotal = foodPriceDiscountTotal;
    }

    public void setFoodFavourites(int foodFavourites) {
        this.foodFavourites = foodFavourites;
    }

    public void setButtonPosition(int buttonPosition) {
        this.buttonPosition = buttonPosition;
    }

    public void setFoodItemCount(int foodItemCount) {
        this.foodItemCount = foodItemCount;
    }

    public void setFoodImg(int foodImg) {
        this.foodImg = foodImg;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public int getFoodPriceDiscount() {
        return foodPriceDiscount;
    }

    public int getFoodPriceTotal() {
        return foodPriceTotal;
    }

    public int getFoodPriceDiscountTotal() {
        return foodPriceDiscountTotal;
    }

    public int getFoodFavourites() {
        return foodFavourites;
    }

    public int getButtonPosition() {
        return buttonPosition;
    }

    public int getFoodItemCount() {
        return foodItemCount;
    }

    public int getFoodImg() {
        return foodImg;
    }
}
