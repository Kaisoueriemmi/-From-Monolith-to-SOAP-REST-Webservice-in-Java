package com.datamart.service;

import com.datamart.dto.ProductRequest;
import com.datamart.dto.ProductResponse;
import com.datamart.exception.ProductNotFoundException;
import com.datamart.model.Product;
import com.datamart.repository.ProductRepository;

import com.datamart.pattern.ElectronicsProductFactory;
import com.datamart.pattern.FoodProductFactory;
import com.datamart.pattern.ProductFactory;
import com.datamart.pattern.InventoryEvent;
import com.datamart.pattern.InventoryEventPublisher;
import com.datamart.pattern.LoggingInventoryObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class InventoryServiceImpl implements InventoryService {
    private final ProductRepository repo;
    private final InventoryEventPublisher eventPublisher;

    @Autowired
    public InventoryServiceImpl(ProductRepository repo) {
        this.repo = repo;
        this.eventPublisher = new InventoryEventPublisher();
        this.eventPublisher.addObserver(new LoggingInventoryObserver());
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        ProductFactory factory;
        String category = request.getCategory();
        if ("Electronics".equalsIgnoreCase(category)) {
            factory = new ElectronicsProductFactory();
        } else if ("Food".equalsIgnoreCase(category)) {
            factory = new FoodProductFactory();
        } else {
            throw new IllegalArgumentException("Unknown category: " + category);
        }
        Product product = factory.createProduct(request.getName(), request.getPrice(), request.getQuantity());
        Product saved = repo.save(product);
        eventPublisher.publishEvent(new InventoryEvent("CREATE", "Product created: " + saved.getName() + " (ID: " + saved.getId() + ")"));
        return toResponse(saved);
    }

    @Override
    public ProductResponse getProduct(int id) {
        Product product = repo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        return toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(int id, ProductRequest request) {
        Product product = repo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        Product updated = repo.save(product);
        eventPublisher.publishEvent(new InventoryEvent("UPDATE", "Product updated: " + updated.getName() + " (ID: " + updated.getId() + ")"));
        return toResponse(updated);
    }

    @Override
    public void deleteProduct(int id) {
        if (!repo.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
        Product product = repo.findById(id).orElse(null);
        repo.deleteById(id);
        if (product != null) {
            eventPublisher.publishEvent(new InventoryEvent("DELETE", "Product deleted: " + product.getName() + " (ID: " + product.getId() + ")"));
        }
    }

    @Override
    public List<ProductResponse> listProducts() {
        return repo.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice(),
                product.getCategory()
        );
    }
}
