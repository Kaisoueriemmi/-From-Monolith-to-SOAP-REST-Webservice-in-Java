package com.datamart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Min(value = 0, message = "Quantity must be >= 0")
    private int quantity;

    @Min(value = 0, message = "Price must be >= 0.0")
    private double price;


    private String category;

    public ProductRequest() {}

    public ProductRequest(String name, int quantity, double price, String category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
