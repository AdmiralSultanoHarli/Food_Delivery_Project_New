package com.example.fooddeliveryproject.Activities.Model;

public class DataTransactionDone {

    int id;
    String shopName;
    int totalPayment;
    String date;
    String paymentMethod;
    int shopImg;
    String orderTracker;

    public DataTransactionDone() {


    }

    public DataTransactionDone(int id, String shopName, int totalPayment, String date, String paymentMethod, String orderTracker, int shopImg) {
        this.id = id;
        this.shopName = shopName;
        this.totalPayment = totalPayment;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.orderTracker = orderTracker;
        this.shopImg = shopImg;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setOrderTracker(String orderTracker) {
        this.orderTracker = orderTracker;
    }

    public void setShopImg(int shopImg) {
        this.shopImg = shopImg;
    }



    public int getId() {
        return id;
    }

    public String getShopName() {
        return shopName;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public String getDate() {
        return date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getOrderTracker() {
        return orderTracker;
    }

    public int getShopImg() {
        return shopImg;
    }

    @Override
    public String toString() {
        return "DataTransactionDone{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", totalPayment=" + totalPayment +
                ", date='" + date + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", shopImg=" + shopImg +
                '}';
    }
}
