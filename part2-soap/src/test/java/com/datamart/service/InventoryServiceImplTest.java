package com.datamart.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.datamart.model.Product;

public class InventoryServiceImplTest {

    private InventoryServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new InventoryServiceImpl();
    }

    @Test
    public void testCreateAndRetrieveProduct() {
        // Create a product
        String result = service.createProduct(1, "Laptop", 10, 999.99);
        assertTrue(result.contains("successfully"));

        // Retrieve the product
        Product p = service.getProduct(1);
        assertNotNull(p);
        assertEquals("Laptop", p.getName());
        assertEquals(10, p.getQuantity());
        assertEquals(999.99, p.getPrice());
    }

    @Test
    public void testCreateProductWithDuplicateId() {
        // Create first product
        service.createProduct(2, "Mouse", 50, 29.99);

        // Try to create another with same ID
        assertThrows(jakarta.xml.ws.WebServiceException.class, () -> {
            service.createProduct(2, "Keyboard", 30, 79.99);
        });
    }

    @Test
    public void testCreateProductWithInvalidQuantity() {
        // Try to create product with negative quantity
        assertThrows(jakarta.xml.ws.WebServiceException.class, () -> {
            service.createProduct(3, "Monitor", -5, 299.99);
        });
    }

    @Test
    public void testCreateProductWithInvalidPrice() {
        // Try to create product with negative price
        assertThrows(jakarta.xml.ws.WebServiceException.class, () -> {
            service.createProduct(4, "Desk", 1, -49.99);
        });
    }

    @Test
    public void testCreateProductWithEmptyName() {
        // Try to create product with empty name
        assertThrows(jakarta.xml.ws.WebServiceException.class, () -> {
            service.createProduct(5, "", 10, 50.00);
        });
    }

    @Test
    public void testUpdateProduct() {
        // Create a product
        service.createProduct(6, "Chair", 20, 199.99);

        // Update it
        String result = service.updateProduct(6, "Gaming Chair", 15, 249.99);
        assertTrue(result.contains("successfully"));

        // Verify changes
        Product p = service.getProduct(6);
        assertEquals("Gaming Chair", p.getName());
        assertEquals(15, p.getQuantity());
        assertEquals(249.99, p.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        // Create a product
        service.createProduct(7, "Headphones", 30, 149.99);

        // Delete it
        String result = service.deleteProduct(7);
        assertTrue(result.contains("successfully"));

        // Verify it's deleted
        assertThrows(jakarta.xml.ws.WebServiceException.class, () -> {
            service.getProduct(7);
        });
    }

    @Test
    public void testListProducts() {
        // Create multiple products
        service.createProduct(8, "Tablet", 5, 599.99);
        service.createProduct(9, "Smartphone", 20, 799.99);

        // List all products
        List<Product> products = service.listProducts();
        assertNotNull(products);
        assertTrue(products.size() >= 2);
    }

    @Test
    public void testGetNonExistentProduct() {
        assertThrows(jakarta.xml.ws.WebServiceException.class, () -> {
            service.getProduct(999);
        });
    }
}