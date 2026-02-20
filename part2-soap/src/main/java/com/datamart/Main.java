package com.datamart;

import jakarta.xml.ws.Endpoint;
import com.datamart.service.InventoryServiceImpl;

public class Main {

    public static void main(String[] args) {
        try {
            InventoryServiceImpl implementor = new InventoryServiceImpl();
            String address = "http://localhost:8080/inventory";
            Endpoint.publish(address, implementor);
            System.out.println("Inventory SOAP Service started at: " + address);
            System.out.println("WSDL available at: " + address + "?wsdl");
            System.out.println("Press enter to stop the service...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}