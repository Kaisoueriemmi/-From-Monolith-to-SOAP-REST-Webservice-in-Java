package com.datamart.pattern;

import com.datamart.model.Product;

public class ElectronicsProductFactory implements ProductFactory {
    @Override
    public Product createProduct(String name, double price, int quantity) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory("Electronics");
        return product;
    }
}
