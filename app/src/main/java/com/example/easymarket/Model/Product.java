package com.example.easymarket.Model;

public class Product {

    private long id;
    private String name;
    private String category;
    private String description;
    private long quantity;
    private long price;

    public Product(String name, String category, String description, long quantity, long price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(long id, String name, String category, String description, long quantity, long price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
