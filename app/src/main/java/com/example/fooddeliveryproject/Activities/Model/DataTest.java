package com.example.fooddeliveryproject.Activities.Model;

public class DataTest {

    private int id;
    private String foodName;
    private int img;


    public DataTest(int id, String foodName, int img) {
        this.id = id;
        this.foodName = foodName;
        this.img = img;
    }

    public DataTest() {

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

    public void setId(int id) {
        this.id = id;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setImg(int img) {
        this.img = img;
    }

}

