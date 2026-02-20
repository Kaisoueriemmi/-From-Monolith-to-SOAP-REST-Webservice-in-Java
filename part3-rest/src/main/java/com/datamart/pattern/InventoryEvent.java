package com.datamart.pattern;

public class InventoryEvent {
    private final String type;
    private final String message;

    public InventoryEvent(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
