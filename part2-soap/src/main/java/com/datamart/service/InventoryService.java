package com.datamart.service;

import java.util.List;

import com.datamart.model.Product;

import jakarta.jws.WebService;

@WebService(name = "InventoryService")
public interface InventoryService {

    String createProduct(int id, String name, int quantity, double price);

    Product getProduct(int id);

    String updateProduct(int id, String name, int quantity, double price);

    String deleteProduct(int id);

    List<Product> listProducts();
}