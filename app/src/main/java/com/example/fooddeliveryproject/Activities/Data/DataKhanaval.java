package com.example.fooddeliveryproject.Activities.Data;

public class DataKhanaval {

    String foodName;
    String foodDescription;
    String date;
    String orderTracker;
    int foodPrice;
    int foodPriceDiscount;
    int chartQuantity;
    int img;
    int buttonPosition;
    int btnPosition;

    public DataKhanaval() {

    }


    public DataKhanaval(String foodName, String foodDescription, String date, String orderTracker, int foodPrice, int foodPriceDiscount, int chartQuantity, int img, int buttonPosition) {
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.date = date;
        this.orderTracker = orderTracker;
        this.foodPrice = foodPrice;
        this.foodPriceDiscount = foodPriceDiscount;
        this.chartQuantity = chartQuantity;
        this.img = img;
        this.buttonPosition = buttonPosition;
    }

    public int getChartQuantity() {
        return chartQuantity;
    }

    public int getButtonPosition() {
        return buttonPosition;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public String getDate() {
        return date;
    }

    public String getOrderTracker() {
        return orderTracker;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public int getFoodPriceDiscount() {
        return foodPriceDiscount;
    }

    public int getImg() {
        return img;
    }

    public void setChartQuantity(int chartQuantity) {
        this.chartQuantity = chartQuantity;
    }

    public void setButtonPosition(int buttonPosition) {
        this.buttonPosition = buttonPosition;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOrderTracker(String orderTracker) {
        this.orderTracker = orderTracker;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setFoodPriceDiscount(int foodPriceDiscount) {
        this.foodPriceDiscount = foodPriceDiscount;
    }

    public void setImg(int img) {
        this.img = img;
    }

}
