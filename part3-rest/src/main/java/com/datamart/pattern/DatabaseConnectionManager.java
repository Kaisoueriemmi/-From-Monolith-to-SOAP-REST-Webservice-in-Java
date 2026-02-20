package com.datamart.pattern;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class DatabaseConnectionManager {
    private static volatile DatabaseConnectionManager instance;
    private final DataSource dataSource;

    private DatabaseConnectionManager() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/inventory");
        config.setUsername("inventory");
        config.setPassword("inventory");
        config.setDriverClassName("org.postgresql.Driver");
        this.dataSource = new HikariDataSource(config);
    }

    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnectionManager.class) {
                if (instance == null) {
                    instance = new DatabaseConnectionManager();
                }
            }
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
