package com.datamart.pattern;

import com.datamart.model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductFactoryTest {
    @Test
    void testElectronicsProductFactory() {
        ProductFactory factory = new ElectronicsProductFactory();
        Product p = factory.createProduct("TV", 499.99, 5);
        assertEquals("Electronics", p.getCategory());
        assertEquals("TV", p.getName());
        assertEquals(499.99, p.getPrice());
        assertEquals(5, p.getQuantity());
    }

    @Test
    void testFoodProductFactory() {
        ProductFactory factory = new FoodProductFactory();
        Product p = factory.createProduct("Apple", 0.99, 100);
        assertEquals("Food", p.getCategory());
        assertEquals("Apple", p.getName());
        assertEquals(0.99, p.getPrice());
        assertEquals(100, p.getQuantity());
    }
}
