package com.example.bth2_5;

public class Dish {
    private String name;
    private int thumbnail;
    private boolean isPromotion;
    public Dish(String name, int thumbnail, boolean isPromotion) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.isPromotion = isPromotion;
    }
    public Dish(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.isPromotion = true;
    }
    public String getName() {
        return name;
    }
    public int getThumbnail() {
        return thumbnail;
    }
    public boolean isPromotion() {
        return isPromotion;
    }
    public void setPromotion(boolean isPromotion) {
        this.isPromotion = isPromotion;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
