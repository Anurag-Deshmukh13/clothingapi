package com.clothing.clothingapi;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    private int id;

    private String title;
    private double price;

    @Column(length = 2000)
    private String description;

    private String category;
    private String image;

    private double rate;
    private int count;

    public Product() {}

    public Product(int id, String title, double price,
                   String description, String category,
                   String image, double rate, int count) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rate = rate;
        this.count = count;
    }

    // getters & setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
}