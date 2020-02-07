package com.example.fooddeliveryproject.Activities.Model;

public class DataTransaction {

    int foodId;
    String foodTransName;
    String foodTransDesc;
    int foodTransPrice;
    int foodTransPriceDiscount;
    int foodTransPriceTotal;
    int foodTransPriceDiscountTotal;
    int buttonTransPosition;
    int foodTransItemCount;
    int foodImg;

    public DataTransaction() {


    }

    public DataTransaction(int foodId, String foodTransName, String foodTransDesc, int foodTransPrice, int foodTransPriceDiscount, int foodTransPriceTotal, int foodTransPriceDiscountTotal, int buttonTransPosition, int foodTransItemCount, int foodImg) {
        this.foodId = foodId;
        this.foodTransName = foodTransName;
        this.foodTransDesc = foodTransDesc;
        this.foodTransPrice = foodTransPrice;
        this.foodTransPriceDiscount = foodTransPriceDiscount;
        this.foodTransPriceTotal = foodTransPriceTotal;
        this.foodTransPriceDiscountTotal = foodTransPriceDiscountTotal;
        this.buttonTransPosition = buttonTransPosition;
        this.foodTransItemCount = foodTransItemCount;
        this.foodImg = foodImg;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setFoodImg(int foodImg) {
        this.foodImg = foodImg;
    }

    public void setFoodTransName(String foodTransName) {
        this.foodTransName = foodTransName;
    }

    public void setFoodTransDesc(String foodTransDesc) {
        this.foodTransDesc = foodTransDesc;
    }

    public void setFoodTransPrice(int foodTransPrice) {
        this.foodTransPrice = foodTransPrice;
    }

    public void setFoodTransPriceDiscount(int foodTransPriceDiscount) {
        this.foodTransPriceDiscount = foodTransPriceDiscount;
    }

    public void setFoodTransPriceTotal(int foodTransPriceTotal) {
        this.foodTransPriceTotal = foodTransPriceTotal;
    }

    public void setFoodTransPriceDiscountTotal(int foodTransPriceDiscountTotal) {
        this.foodTransPriceDiscountTotal = foodTransPriceDiscountTotal;
    }

    public void setButtonTransPosition(int buttonTransPosition) {
        this.buttonTransPosition = buttonTransPosition;
    }

    public void setFoodTransItemCount(int foodTransItemCount) {
        this.foodTransItemCount = foodTransItemCount;
    }


    public String getFoodTransName() {
        return foodTransName;
    }

    public String getFoodTransDesc() {
        return foodTransDesc;
    }

    public int getFoodTransPrice() {
        return foodTransPrice;
    }

    public int getFoodTransPriceDiscount() {
        return foodTransPriceDiscount;
    }

    public int getFoodTransPriceTotal() {
        return foodTransPriceTotal;
    }

    public int getFoodTransPriceDiscountTotal() {
        return foodTransPriceDiscountTotal;
    }

    public int getButtonTransPosition() {
        return buttonTransPosition;
    }

    public int getFoodTransItemCount() {
        return foodTransItemCount;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getFoodImg() {
        return foodImg;
    }

    @Override
    public String toString() {
        return "DataTransaction{" +
                "foodId=" + foodId +
                ", foodTransName='" + foodTransName + '\'' +
                ", foodTransDesc='" + foodTransDesc + '\'' +
                ", foodTransPrice=" + foodTransPrice +
                ", foodTransPriceDiscount=" + foodTransPriceDiscount +
                ", foodTransPriceTotal=" + foodTransPriceTotal +
                ", foodTransPriceDiscountTotal=" + foodTransPriceDiscountTotal +
                ", buttonTransPosition=" + buttonTransPosition +
                ", foodTransItemCount=" + foodTransItemCount +
                ", foodImg=" + foodImg +
                '}';
    }
}
