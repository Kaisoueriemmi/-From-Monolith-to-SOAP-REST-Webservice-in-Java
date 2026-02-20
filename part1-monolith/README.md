# Part 1 - Monolithic Console Application

This is the first part of Lab2, implementing a monolithic Java console application for DataMart's inventory management.

## How to Run

1. Start the PostgreSQL database:
   ```bash
   docker compose up -d
   ```

2. Build and run the application:
   ```bash
   mvn clean compile exec:java -Dexec.mainClass="com.datamart.Main"
   ```

## Reflection Question

What are the main limitations of this monolithic architecture when the business grows and multiple teams need to use the inventory data?

The main limitations include:

- **Tight Coupling**: All components (UI, business logic, data access) are tightly coupled in a single codebase, making it difficult to modify or scale individual parts without affecting others.

- **Scalability Issues**: As the business grows, the monolithic application becomes harder to scale. Different teams may need different parts of the system, but they have to deploy the entire application.

- **Technology Lock-in**: The entire system is built with the same technology stack, limiting flexibility for teams that might prefer different languages or frameworks.

- **Deployment Complexity**: Any change requires redeploying the entire application, increasing risk and downtime.

- **Team Coordination**: Multiple teams working on the same codebase can lead to conflicts and slower development cycles.

- **Maintenance Challenges**: Over time, the codebase grows, making it harder to understand, test, and maintain.

- **Resource Utilization**: The application runs as a single unit, so it's difficult to scale specific components that might have higher load (e.g., if inventory queries are frequent but updates are rare).

In contrast, a service-oriented architecture would allow different teams to develop, deploy, and scale their services independently, using the best tools for their needs.