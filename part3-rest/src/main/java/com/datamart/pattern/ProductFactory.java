package com.datamart.pattern;

import com.datamart.model.Product;

public interface ProductFactory {
    Product createProduct(String name, double price, int quantity);
}
