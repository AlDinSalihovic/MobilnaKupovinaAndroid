package com.example.projekt2september;

public class Names {

    String name;
    String price;
    int pic;

    public Names(String name, String price, int pic) {
        this.name = name;
        this.price = price;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPicture() {
        return pic;
    }

    public void setPicture(int pic) {
        this.pic = pic;
    }
}
