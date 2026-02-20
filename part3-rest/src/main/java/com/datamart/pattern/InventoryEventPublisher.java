package com.datamart.pattern;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InventoryEventPublisher {
    private final List<InventoryObserver> observers = new CopyOnWriteArrayList<>();

    public void addObserver(InventoryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(InventoryObserver observer) {
        observers.remove(observer);
    }

    public void publishEvent(InventoryEvent event) {
        for (InventoryObserver observer : observers) {
            observer.onInventoryEvent(event);
        }
    }
}
