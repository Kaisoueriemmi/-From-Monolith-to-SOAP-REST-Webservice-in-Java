# Lab 2 — From Monolith to SOAP & REST Webservice in Java

**Instructor:** Hamza Gbada  
**Course:** Service And API  
**Duration:** 4 hours  
**Language:** Java 17+

## 📝 Overview

This lab guides you through the process of modernizing a monolithic application managed by "DataMart". You will migrate from a monolithic Java console application to a full **Service-Oriented Architecture (SOA)** using both **SOAP** and **REST** webservices, followed by the application of fundamental design patterns.

## 🏗️ Project Structure

The project is divided into four main parts:

1.  **`part1-monolith/`**: Monolithic Console Application (Plain Java + JDBC/JPA).
2.  **`part2-soap/`**: SOAP Webservice (JAX-WS / Apache CXF + PostgreSQL).
3.  **`part3-rest/`**: RESTful Webservice (Spring Boot + JPA/Hibernate).
4.  **Design Patterns**: Implementation of Singleton, Factory, and Observer patterns (integrated into the services).

## 🚀 Objectives

- Understand the limitations of monolithic architectures.
- Implement and expose service contracts using **WSDL (SOAP)**.
- Design and develop **RESTful APIs** following HTTP semantics.
- Apply GoF design patterns (**Singleton, Factory, Observer**) to improve service logic.

## 🛠️ Environment Setup

### Required Tools

| Tool                 | Version | Purpose                          |
| :------------------- | :------ | :------------------------------- |
| **JDK (OpenJDK)**    | 17+     | Java compiler & runtime          |
| **Maven**            | 3.9+    | Build & dependency management    |
| **Docker & Compose** | 20+     | Container runtime for PostgreSQL |
| **PostgreSQL**       | 15      | Relational database              |
| **Postman / SoapUI** | Latest  | API testing                      |

### 🗄️ Database Configuration

All parts share the same PostgreSQL database container. You can start the database using the provided `docker-compose.yml` at the root:

```bash
docker-compose up -d
```

**Connection Details:**

- **JDBC URL:** `jdbc:postgresql://localhost:5432/inventory`
- **User:** `inventory`
- **Password:** `inventory`
- **Database:** `inventory`

## 📊 Data Model

Every part of this lab uses the same `Product` entity:

| Field      | Type   | Constraints         |
| :--------- | :----- | :------------------ |
| `id`       | int    | Primary key, Unique |
| `name`     | String | Not null, Not empty |
| `quantity` | int    | ≥ 0                 |
| `price`    | double | ≥ 0.0               |

---

_Created as part of the Service and API course._
