package com.datamart.controller;

import com.datamart.dto.ProductRequest;
import com.datamart.model.Product;
import com.datamart.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository repo;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        repo.deleteAll();
    }

    @Test
        void testCreateAndGetProduct() throws Exception {
        ProductRequest req = new ProductRequest("Laptop", 10, 1299.99, "Electronics");
        String json = objectMapper.writeValueAsString(req);
        mockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value("Laptop"))
            .andExpect(jsonPath("$.category").value("Electronics"));

        Product p = repo.findAll().get(0);
        mockMvc.perform(get("/api/products/" + p.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Laptop"))
            .andExpect(jsonPath("$.category").value("Electronics"));
        }

    @Test
    void testUpdateProduct() throws Exception {
        Product p = repo.save(new Product("Phone", 5, 599.99, "Electronics"));
        ProductRequest req = new ProductRequest("Smartphone", 8, 699.99, "Electronics");
        String json = objectMapper.writeValueAsString(req);
        mockMvc.perform(put("/api/products/" + p.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Smartphone"))
                .andExpect(jsonPath("$.quantity").value(8))
                .andExpect(jsonPath("$.category").value("Electronics"));
    }

    @Test
        void testDeleteProduct() throws Exception {
        Product p = repo.save(new Product("Tablet", 3, 399.99, "Electronics"));
        mockMvc.perform(delete("/api/products/" + p.getId()))
            .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/products/" + p.getId()))
            .andExpect(status().isNotFound());
        }
}
