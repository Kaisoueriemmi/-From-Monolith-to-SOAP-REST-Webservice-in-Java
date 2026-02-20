package com.datamart.service;

import java.util.List;

import com.datamart.model.Product;
import com.datamart.repository.ProductRepository;

import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

@WebService(endpointInterface = "com.datamart.service.InventoryService")
public class InventoryServiceImpl implements InventoryService {

    private ProductRepository repo;

    public InventoryServiceImpl() {
        this.repo = new ProductRepository();
    }

    @Override
    public String createProduct(int id, String name, int quantity, double price) {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new WebServiceException("Product name cannot be empty");
            }
            if (quantity < 0) {
                throw new WebServiceException("Quantity must be >= 0");
            }
            if (price < 0.0) {
                throw new WebServiceException("Price must be >= 0.0");
            }

            // Check if product with ID already exists
            if (repo.findById(id) != null) {
                throw new WebServiceException("Product with ID " + id + " already exists");
            }

            Product p = new Product(name, quantity, price);
            p.setId(id);
            repo.save(p);
            return "Product created successfully with ID: " + id;
        } catch (Exception e) {
            throw new WebServiceException("Error creating product: " + e.getMessage());
        }
    }

    @Override
    public Product getProduct(int id) {
        try {
            Product p = repo.findById(id);
            if (p == null) {
                throw new WebServiceException("Product with ID " + id + " not found");
            }
            return p;
        } catch (Exception e) {
            throw new WebServiceException("Error retrieving product: " + e.getMessage());
        }
    }

    @Override
    public String updateProduct(int id, String name, int quantity, double price) {
        try {
            Product p = repo.findById(id);
            if (p == null) {
                throw new WebServiceException("Product with ID " + id + " not found");
            }
            if (name == null || name.trim().isEmpty()) {
                throw new WebServiceException("Product name cannot be empty");
            }
            if (quantity < 0) {
                throw new WebServiceException("Quantity must be >= 0");
            }
            if (price < 0.0) {
                throw new WebServiceException("Price must be >= 0.0");
            }

            p.setName(name);
            p.setQuantity(quantity);
            p.setPrice(price);
            repo.update(p);
            return "Product updated successfully";
        } catch (Exception e) {
            throw new WebServiceException("Error updating product: " + e.getMessage());
        }
    }

    @Override
    public String deleteProduct(int id) {
        try {
            Product p = repo.findById(id);
            if (p == null) {
                throw new WebServiceException("Product with ID " + id + " not found");
            }
            repo.delete(id);
            return "Product deleted successfully";
        } catch (Exception e) {
            throw new WebServiceException("Error deleting product: " + e.getMessage());
        }
    }

    @Override
    public List<Product> listProducts() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new WebServiceException("Error listing products: " + e.getMessage());
        }
    }
}