package com.example.eps;

import java.io.Serializable;

public class ShapeModel implements Serializable {
    private String firstBtnName;
    private String secondBtnName;
    private String itemName;
    private int image;

    public ShapeModel(String itemName, int image) {
        this.itemName = itemName;
        this.image = image;
    }

    public String getFirstBtnName() {
        return firstBtnName;
    }

    public void setFirstBtnName(String firstBtnName) {
        this.firstBtnName = firstBtnName;
    }

    public String getSecondBtnName() {
        return secondBtnName;
    }

    public void setSecondBtnName(String secondBtnName) {
        this.secondBtnName = secondBtnName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ShapeModel(String firstBtnName, String secondBtnName, String itemName, int image) {
        this.firstBtnName = firstBtnName;
        this.secondBtnName = secondBtnName;
        this.itemName = itemName;
        this.image = image;
    }


}
