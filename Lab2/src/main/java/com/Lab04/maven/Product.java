package com.Lab04.maven;

public class Product {
    private int id;
    private String name;
    private int price;
    private String color;


    public Product() {
        super();
    }


    public Product(int id, String name, int price, String color) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String Name) {
        this.name = name;
    }


    public float getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }

    public String toString (){
        return "Product{" + getId() + ", " + getName() + ", " + getPrice() + ", " + getColor() + "}";
    }
}
