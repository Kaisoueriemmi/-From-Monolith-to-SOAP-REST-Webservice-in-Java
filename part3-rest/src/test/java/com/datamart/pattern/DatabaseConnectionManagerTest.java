package com.datamart.pattern;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionManagerTest {
    @Test
    void testSingletonInstance() throws Exception {
        DatabaseConnectionManager[] instances = new DatabaseConnectionManager[2];
        Thread t1 = new Thread(() -> instances[0] = DatabaseConnectionManager.getInstance());
        Thread t2 = new Thread(() -> instances[1] = DatabaseConnectionManager.getInstance());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertSame(instances[0], instances[1], "Instances should be the same (singleton)");
    }
}
