# Food Delivery Backend

A production-inspired RESTful food delivery backend built with **Java 21 and Spring Boot 4**, designed around a layered architecture and real-world food delivery workflows similar to platforms such as Swiggy and Zomato.

The system supports multiple user roles, JWT-based authentication, restaurant and menu management, cart and order workflows, delivery tracking, payment simulation, reviews, notifications, admin operations, Redis caching, and Dockerized infrastructure.

---

## Features

### Authentication & Authorization

* User registration and login
* JWT-based authentication
* BCrypt password hashing
* Role-based authorization
* Custom JWT authentication filter
* Custom authentication entry point
* Custom access denied handler
* Current authenticated user resolution

### User Roles

* `CUSTOMER`
* `OWNER`
* `DELIVERY_PARTNER`
* `ADMIN`

---

## Restaurant Management

Restaurant owners can:

* Create restaurants
* Update restaurant details
* Delete restaurants
* Open and close restaurants

Customers can:

* View restaurants
* Search restaurants
* Search restaurants by city

Admins can:

* View pending restaurants
* Approve restaurants
* Reject restaurants

### Restaurant Lifecycle

```text
PENDING
   |
   +----> APPROVED
   |
   +----> REJECTED
```

Approved restaurants can be opened or closed by authorized users.

---

## Menu Management

Restaurant owners can:

* Create menu items
* Update menu items
* Delete menu items
* View restaurant menus
* View available menu items
* Search menu items

Menu access is protected using restaurant ownership authorization.

---

## Address Management

Customers can:

* Add addresses
* Update addresses
* Delete addresses
* View addresses
* Set a default address

---

## Cart Management

Customers can:

* Add items to cart
* Update quantities
* Remove items
* View their cart

---

## Order Management

The application implements a complete order lifecycle with business-rule validation.

### Order Lifecycle

```text
PLACED
   ↓
ACCEPTED
   ↓
PREPARING
   ↓
READY_FOR_PICKUP
   ↓
OUT_FOR_DELIVERY
   ↓
DELIVERED
```

Alternative states include:

```text
REJECTED
CANCELLED
```

Order state transitions are implemented through dedicated business operations rather than a generic status update endpoint.

---

## Delivery Management

Restaurant owners can assign delivery partners to orders.

Delivery partners can progress through:

```text
ASSIGNED
   ↓
ACCEPTED
   ↓
ARRIVED_AT_RESTAURANT
   ↓
PICKED_UP
   ↓
DELIVERED
```

The system also validates delivery-partner availability.

Order status is automatically updated when delivery milestones are reached.

---

## Payment

Payment processing is simulated for the backend project.

### Supported Payment Methods

* Cash on Delivery
* Card
* UPI
* Net Banking
* Wallet

### Payment Statuses

* `PENDING`
* `SUCCESS`
* `FAILED`
* `REFUNDED`

Online payment methods are simulated as successful transactions, while COD remains pending.

---

## Reviews & Ratings

Customers can review restaurants.

The system:

* Stores customer reviews
* Associates reviews with restaurants
* Updates restaurant ratings based on reviews
* Prevents unauthorized review operations

---

## Notifications

The backend provides a database-backed notification system.

Users can:

* View notifications
* View unread notifications
* Mark notifications as read
* Mark all notifications as read

Notifications are integrated with major workflows including:

* Orders
* Payments
* Deliveries
* Restaurant operations
* Admin operations

---

## Admin Module

The admin module provides operational management and monitoring.

### Dashboard

Provides counts for:

* Users
* Restaurants
* Orders
* Payments
* Deliveries

### User Management

Admins can:

* View all users
* View individual users
* Activate users
* Deactivate users

### Monitoring

Admins can monitor:

* Orders
* Payments
* Deliveries

### Restaurant Moderation

Admins can:

* View pending restaurants
* Approve restaurants
* Reject restaurants

---

# Redis Caching

Redis is used to cache frequently accessed restaurant and menu data.

### Cached Operations

Restaurant:

* Restaurant details
* Restaurant lists
* Restaurant search
* Restaurants by city
* Pending restaurants

Menu:

* Menu item details
* Restaurant menus
* Available menus
* Menu search

### Cache Management

The project uses Spring Cache with:

* `@Cacheable`
* `@CacheEvict`
* Redis `RedisCacheManager`
* Configurable TTLs
* Cache invalidation after data changes

### Performance Benchmark

A local Docker benchmark was performed after warming up the application and database connection pool.

| Metric                    | Direct MySQL | Redis Cache |
| ------------------------- | -----------: | ----------: |
| Average warm-read latency |     ~13.5 ms |    ~10.5 ms |
| Improvement               |            — |        ~22% |

**Result:** Redis caching reduced average warm-read latency by approximately **22%** for the tested warm-read scenario.

> Benchmark results were obtained locally using the Dockerized application and may vary depending on hardware, workload, and environment.

---

# Docker

The application is fully containerized for local development.

### Docker Services

```text
                 Docker Compose
                       |
        +--------------+--------------+
        |              |              |
        v              v              v
   Spring Boot       MySQL          Redis
   Port 8080        Port 3306      Port 6379
```

### Included

* Multi-stage Dockerfile
* Docker Compose
* MySQL 8.4
* Redis 7
* Persistent MySQL volume
* Health checks
* Docker network
* Environment variables

### Start the Application

```bash
docker compose up --build
```

The backend will be available at:

```text
http://localhost:8080
```

To run in detached mode:

```bash
docker compose up --build -d
```

To stop the application:

```bash
docker compose down
```

---

# API Documentation

The REST APIs are documented using **OpenAPI / Swagger UI**.

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI specification:

```text
http://localhost:8080/v3/api-docs
```

Swagger includes JWT Bearer authentication, allowing protected APIs to be tested directly from the documentation interface.

---

# Architecture

The project follows a layered architecture:

```text
                REST Controller
                       |
                       v
                    Service
                       |
                       v
                   Repository
                       |
                       v
                     MySQL
```

Redis is used as a caching layer for frequently accessed data:

```text
                    Client
                       |
                       v
                  Controller
                       |
                       v
                    Service
                       |
                 +-----+-----+
                 |           |
              Redis        MySQL
              Cache       Database
```

### Design Principles

* Separation of concerns
* DTO-based API responses
* Entity/DTO mapping
* Constructor injection
* Business logic inside services
* Repository abstraction
* Centralized exception handling
* Validation
* Role-based authorization

---

# Project Structure

```text
src/main/java/com/fooddelivery/food_delivery_backend
│
├── admin
├── address
├── auth
├── cart
├── common
├── config
├── customer
├── delivery
├── menu
├── notification
├── order
├── payment
├── restaurant
├── review
├── security
└── user
```

Most feature modules follow the structure:

```text
controller
service
service.impl
repository
entity
dto
mapper
enum
```

---

# Security

Protected endpoints use JWT authentication.

The authentication flow is:

```text
Login
  |
  v
Validate credentials
  |
  v
Generate JWT
  |
  v
Client sends JWT
  |
  v
JWT Filter
  |
  v
Authenticate request
  |
  v
Role-based authorization
```

Public endpoints include authentication and Swagger documentation.

Administrative endpoints require the `ADMIN` authority.

---

# Database

The application uses:

* MySQL 8.4
* Spring Data JPA
* Hibernate

Hibernate manages the entity-to-database mapping.

The database contains entities supporting:

* Users
* Restaurants
* Menu items
* Addresses
* Cart
* Orders
* Order items
* Deliveries
* Payments
* Reviews
* Notifications

---

# Technology Stack

| Technology        | Purpose                         |
| ----------------- | ------------------------------- |
| Java 21           | Programming language            |
| Spring Boot 4     | Backend framework               |
| Spring MVC        | REST APIs                       |
| Spring Data JPA   | Data access                     |
| Hibernate         | ORM                             |
| Spring Security   | Authentication & authorization  |
| JWT               | Stateless authentication        |
| BCrypt            | Password hashing                |
| MySQL 8.4         | Relational database             |
| Redis 7           | Caching                         |
| Docker            | Containerization                |
| Docker Compose    | Multi-container environment     |
| Swagger / OpenAPI | API documentation               |
| Maven             | Build and dependency management |
| Lombok            | Boilerplate reduction           |

---

# Running Locally

## Prerequisites

* Java 21
* Maven
* Docker Desktop
* Git

Docker is recommended because MySQL and Redis are included in the Docker Compose environment.

## Clone the Repository

```bash
git clone <https://github.com/AnishKadu-debug/food-delivery-backend>
cd food-delivery-backend
```

## Start with Docker

```bash
docker compose up --build
```

The application starts with:

```text
Spring Boot
MySQL
Redis
```

---

# Git Workflow

Development was performed using a dedicated `develop` branch.

Feature implementations were committed incrementally as the project evolved.

Example:

```bash
git add .
git commit -m "Add Redis Caching with Docker"
git push origin develop
```

The `main` branch is reserved for the stable version of the project.

---

# Current Project Status

### Completed

* Authentication
* JWT Security
* Role-based authorization
* User management
* Restaurant management
* Menu management
* Address management
* Cart
* Order lifecycle
* Delivery lifecycle
* Payment simulation
* Reviews and ratings
* Notifications
* Admin dashboard
* Admin monitoring
* Admin user management
* Swagger/OpenAPI
* Docker
* MySQL containerization
* Redis caching
* Cache invalidation
* Redis performance benchmarking

### Future Improvements

Planned improvements include:

* Comprehensive unit and integration testing
* Structured application logging
* Production-specific configuration profiles
* Cloud deployment
* CI/CD pipeline
* Application monitoring with Spring Boot Actuator

---

# Author

**Anish**

Food Delivery Backend built as a production-inspired Java/Spring Boot backend project for learning, portfolio development, and backend engineering practice.
