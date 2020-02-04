package com.example.fooddeliveryproject.Activities.Model;

public class DataKhanaval {

    int id;
    String foodName;
    String foodDescription;
    String date;
    String orderTracker;
    String address;
    int foodPrice;
    int foodPriceDiscount;
    int chartQuantity;
    int img;
    int buttonPosition;
    int foodItemCount;

    boolean button;
    int foodQuantity;
    int btnPosition;

    public DataKhanaval(){



    }

    public DataKhanaval(int id, String foodName, String address, int img){

        this.id = id;
        this.foodName = foodName;
        this.address = address;
        this.img = img;

    }


    public DataKhanaval(int id, String foodName, int img) {

        this.id = id;
        this.foodName = foodName;
        this.img = img;

    }

    public DataKhanaval(int id, String foodName, String foodDescription, int foodPrice, int foodPriceDiscount, int buttonPosition, int foodItemCount, int img) {
        this.id = id;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.foodPriceDiscount = foodPriceDiscount;
        this.buttonPosition = buttonPosition;
        this.foodItemCount = foodItemCount;
        this.img = img;
    }

   /* public DataKhanaval(String foodName, String foodDescription, String date, String orderTracker, int foodPrice, int foodPriceDiscount, int chartQuantity, int img, int buttonPosition) {
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.date = date;
        this.orderTracker = orderTracker;
        this.foodPrice = foodPrice;
        this.foodPriceDiscount = foodPriceDiscount;
        this.chartQuantity = chartQuantity;
        this.img = img;
        this.buttonPosition = buttonPosition;
    }*/

    public String getAddress() {
        return address;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public int getFoodItemCount() {
        return foodItemCount;
    }

    public int getId() {
        return id;
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

    public boolean isButton() {
        return button;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChartQuantity(int chartQuantity) {
        this.chartQuantity = chartQuantity;
    }

    public void setFoodItemCount(int foodItemCount) {
        this.foodItemCount = foodItemCount;
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

    public void setButton(boolean button) {
        this.button = button;
    }
}
