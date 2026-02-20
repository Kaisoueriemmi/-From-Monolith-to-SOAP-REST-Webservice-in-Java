package com.datamart.pattern;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestObserver implements InventoryObserver {
    InventoryEvent lastEvent;
    @Override
    public void onInventoryEvent(InventoryEvent event) {
        lastEvent = event;
    }
}

public class InventoryObserverTest {
    @Test
    void testObserverReceivesEvent() {
        InventoryEventPublisher publisher = new InventoryEventPublisher();
        TestObserver observer = new TestObserver();
        publisher.addObserver(observer);
        InventoryEvent event = new InventoryEvent("TEST", "Test message");
        publisher.publishEvent(event);
        assertNotNull(observer.lastEvent);
        assertEquals("TEST", observer.lastEvent.getType());
        assertEquals("Test message", observer.lastEvent.getMessage());
    }
}
