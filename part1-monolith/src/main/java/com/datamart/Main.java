package com.datamart;

import com.datamart.service.InventoryException;
import com.datamart.service.InventoryService;
import com.datamart.model.Product;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static InventoryService service = new InventoryService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== DataMart Inventory System ===");
            System.out.println("1. Add Product");
            System.out.println("2. View Product by ID");
            System.out.println("3. List All Products");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        viewProduct();
                        break;
                    case 3:
                        listAllProducts();
                        break;
                    case 4:
                        updateProduct();
                        break;
                    case 5:
                        deleteProduct();
                        break;
                    case 6:
                        running = false;
                        service.close();
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addProduct() throws InventoryException {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        service.addProduct(name, quantity, price);
        System.out.println("Product added successfully.");
    }

    private static void viewProduct() {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product p = service.getProductById(id);
        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void listAllProducts() {
        List<Product> products = service.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product p : products) {
                System.out.println(p);
            }
        }
    }

    private static void updateProduct() throws InventoryException {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        service.updateProduct(id, name, quantity, price);
        System.out.println("Product updated successfully.");
    }

    private static void deleteProduct() throws InventoryException {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        service.deleteProduct(id);
        System.out.println("Product deleted successfully.");
    }
}