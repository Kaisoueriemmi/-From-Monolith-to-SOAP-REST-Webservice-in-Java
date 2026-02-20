package com.datamart.service;

import com.datamart.model.Product;
import com.datamart.repository.ProductRepository;

import java.util.List;

public class InventoryService {

    private ProductRepository repo;

    public InventoryService() {
        this.repo = new ProductRepository();
    }

    public void addProduct(String name, int quantity, double price) throws InventoryException {
        if (name == null || name.trim().isEmpty()) {
            throw new InventoryException("Product name cannot be empty");
        }
        if (quantity < 0) {
            throw new InventoryException("Quantity must be >= 0");
        }
        if (price < 0.0) {
            throw new InventoryException("Price must be >= 0.0");
        }
        Product p = new Product(name, quantity, price);
        repo.save(p);
    }

    public Product getProductById(int id) {
        return repo.findById(id);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public void updateProduct(int id, String name, int quantity, double price) throws InventoryException {
        Product p = repo.findById(id);
        if (p == null) {
            throw new InventoryException("Product not found");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new InventoryException("Product name cannot be empty");
        }
        if (quantity < 0) {
            throw new InventoryException("Quantity must be >= 0");
        }
        if (price < 0.0) {
            throw new InventoryException("Price must be >= 0.0");
        }
        p.setName(name);
        p.setQuantity(quantity);
        p.setPrice(price);
        repo.update(p);
    }

    public void deleteProduct(int id) throws InventoryException {
        Product p = repo.findById(id);
        if (p == null) {
            throw new InventoryException("Product not found");
        }
        repo.delete(id);
    }

    public void close() {
        repo.close();
    }
}