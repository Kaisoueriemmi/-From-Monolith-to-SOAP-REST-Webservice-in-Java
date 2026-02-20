package com.datamart.service;

import com.datamart.dto.ProductRequest;
import com.datamart.dto.ProductResponse;
import java.util.List;

public interface InventoryService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse getProduct(int id);
    ProductResponse updateProduct(int id, ProductRequest request);
    void deleteProduct(int id);
    List<ProductResponse> listProducts();
}
