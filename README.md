# 🛰️ Secure Microservices Ecosystem

A distributed order management system built with **Spring Boot 3** and **Spring Cloud**, featuring a secure architecture integrated with **Auth0 (OAuth2)** and **MySQL**.



## 🏗️ Architecture Overview

This project follows a microservices pattern consisting of six distinct services:

### 🛠️ Infrastructure Services
* **Config Server (Port 8888):** Centralized management for service configurations.
* **Service Registry (Port 8761):** Netflix Eureka server for dynamic service discovery.
* **Cloud Gateway (Port 8080):** The single entry point. Handles routing and acts as the **OAuth2 Client**.

### 💼 Business Services
* **Product Service (Port 8081):** Manages product catalog and inventory.
* **Order Service (Port 8082):** Handles order placement and status tracking.
* **Payment Service (Port 8083):** Manages transaction processing and history.

---

## 🔐 Security Architecture (Auth0 + OAuth2)

The system is secured using **Spring Security** and **OAuth2/OIDC** via **Auth0**.



* **Authentication:** Handled at the **Cloud Gateway** level using Auth0 Universal Login.
* **Authorization:** Downstream services act as **Resource Servers**.
* **Token Relay:** The Gateway automatically propagates the JWT (JSON Web Token) to internal services for authenticated requests.

---

## 🚀 Tech Stack

* **Backend:** Java 17, Spring Boot 3.x, Spring Cloud
* **Security:** Spring Security (OAuth2 Client & Resource Server)
* **Identity Provider:** Auth0
* **Database:** MySQL
* **Service Communication:** OpenFeign & Service Discovery
* **Build Tool:** Maven

---

## 🚦 Getting Started

### Prerequisites
* Java 17+
* Maven 3.6+
* MySQL Instance (`localhost:3306`)
* Auth0 Account & Tenant

### Installation & Run Order
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/your-repo-name.git](https://github.com/your-username/your-repo-name.git)
    ```
2.  **Start Services in Order:**
    1. `config-server`
    2. `service-registry`
    3. `product-service`, `order-service`, `payment-service`
    4. `api-gateway`

---

## ⚙️ Environment Configuration

Ensure your `application.yml` files (or Config Server properties) are updated with your credentials:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_db_name
    username: ${DB_USER}
    password: ${DB_PASSWORD}
  security:
    oauth2:
      client:
        registration:
          auth0:
            client-id: ${AUTH0_CLIENT_ID}
            client
