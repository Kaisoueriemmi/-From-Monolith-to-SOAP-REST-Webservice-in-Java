package com.datamart.pattern;

public class LoggingInventoryObserver implements InventoryObserver {
    @Override
    public void onInventoryEvent(InventoryEvent event) {
        System.out.println("[InventoryEvent] " + event.getType() + ": " + event.getMessage());
    }
}
