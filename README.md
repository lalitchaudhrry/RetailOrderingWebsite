This is the ReadMe file of Retail Ordering Website

---

# SpringBites Backend

A Spring Boot–based retail ordering backend that provides secure REST APIs for authentication, product management, cart handling, and order processing.

## Built With

* Spring Boot 3
* Spring Security + JWT
* Role-Based Access (ADMIN / USER)
* Spring Data JPA (Hibernate)
* MySQL
* Global Exception Handling
* Request Validation

---

## Features

### Authentication & Security

* JWT-based authentication
* Role-based authorization
* Stateless session management
* Password encryption using BCrypt
* Protected admin endpoints

### User Capabilities

* Register & login
* Browse products
* Manage cart
* Place orders

### Admin Capabilities

* Manage brands
* Manage categories
* Manage products
* Manage inventory

---

## Project Structure

```
RetailOrderingWebsite
│
├── .idea
├── .mvn
├── src
│   └── main
│       ├── java
│       │   └── com.springbites
│       │       │
│       │       ├── SpringbitesBackendApplication.java
│       │       │
│       │       ├── config
│       │       │   ├── PasswordConfig.java
│       │       │   └── SecurityConfig.java
│       │       │
│       │       ├── controller
│       │       │   ├── AuthController.java
│       │       │   ├── BrandController.java
│       │       │   ├── CartController.java
│       │       │   ├── CategoryController.java
│       │       │   ├── OrderController.java
│       │       │   └── ProductController.java
│       │       │
│       │       ├── dto
│       │       │   ├── AddToCartRequest.java
│       │       │   ├── AuthRequest.java
│       │       │   ├── AuthResponse.java
│       │       │   ├── BrandRequest.java
│       │       │   ├── BrandResponse.java
│       │       │   ├── CartItemResponse.java
│       │       │   ├── CartResponse.java
│       │       │   ├── CategoryRequest.java
│       │       │   ├── CategoryResponse.java
│       │       │   ├── OrderItemResponse.java
│       │       │   ├── OrderResponse.java
│       │       │   ├── PlaceOrderRequest.java
│       │       │   ├── ProductRequest.java
│       │       │   └── ProductResponse.java
│       │       │
│       │       ├── entity
│       │       │   ├── Brand.java
│       │       │   ├── Cart.java
│       │       │   ├── CartItem.java
│       │       │   ├── Category.java
│       │       │   ├── Order.java
│       │       │   ├── OrderItem.java
│       │       │   ├── Product.java
│       │       │   ├── Role.java
│       │       │   └── User.java
│       │       │
│       │       ├── exception
│       │       │   ├── CartEmptyException.java
│       │       │   ├── GlobalExceptionHandler.java
│       │       │   ├── InsufficientStockException.java
│       │       │   └── ResourceNotFoundException.java
│       │       │
│       │       ├── repository
│       │       │   ├── BrandRepository.java
│       │       │   ├── CartRepository.java
│       │       │   ├── CategoryRepository.java
│       │       │   ├── OrderRepository.java
│       │       │   ├── ProductRepository.java
│       │       │   └── UserRepository.java
│       │       │
│       │       ├── security
│       │       │   ├── JwtFilter.java
│       │       │   └── JwtUtil.java
│       │       │
│       │       └── service
│       │           ├── AuthService.java
│       │           ├── BrandService.java
│       │           ├── CartService.java
│       │           ├── CategoryService.java
│       │           ├── OrderService.java
│       │           └── ProductService.java
│       │
│       └── resources
│           └── application.properties
│
└── pom.xml
```

---

## Tech Stack

| Layer      | Technology            |
| ---------- | --------------------- |
| Backend    | Spring Boot 3         |
| Security   | Spring Security + JWT |
| Database   | MySQL                 |
| ORM        | Hibernate / JPA       |
| Build Tool | Maven                 |
| Validation | Jakarta Validation    |

---

## Authentication Flow

1. User registers → `/api/auth/register`
2. User logs in → `/api/auth/login`
3. Server returns JWT token
4. Client sends token in header:

```
Authorization: Bearer <token>
```

5. JwtFilter validates token
6. Role-based access is enforced

---

## API Endpoints

### Public APIs

| Method | Endpoint           | Description   |
| ------ | ------------------ | ------------- |
| POST   | /api/auth/register | Register user |
| POST   | /api/auth/login    | Login user    |

---

### User APIs (Authenticated)

| Method | Endpoint      | Description  |
| ------ | ------------- | ------------ |
| GET    | /api/products | Get products |
| POST   | /api/cart/add | Add to cart  |
| GET    | /api/cart     | View cart    |
| POST   | /api/orders   | Place order  |

---

### Admin APIs (ADMIN only)

| Method | Endpoint                 | Description     |
| ------ | ------------------------ | --------------- |
| POST   | /api/admin/brands        | Create brand    |
| POST   | /api/admin/categories    | Create category |
| POST   | /api/admin/products      | Create product  |
| PUT    | /api/admin/products/{id} | Update product  |
| DELETE | /api/admin/products/{id} | Delete product  |

---

## Postman Testing Guide

### Step 1: Register

**POST** `/api/auth/register`

Body:

```json
{
  "email": "user@gmail.com",
  "password": "123456"
}
```

---

### Step 2: Login

**POST** `/api/auth/login`

Copy the returned JWT token.

---

### Step 3: Use Token

In Postman:

```
Authorization → Bearer Token → <paste token>
```

---

## Testing Admin APIs

Only works if user role = ADMIN.

Either:

* Update role in DB to ADMIN
* or create admin manually in DB

Example SQL:

```sql
update users set role='ADMIN' where email='admin@gmail.com';
```

Then login again to get ADMIN token.

---

## Database Configuration

Example `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/springbites
spring.datasource.username=root
spring.datasource.password=11647908 

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## Running the Project

### 1. Clone repo

```bash
git clone <repo-url>
cd springbites-backend
```

### 2. Configure MySQL

Create database:

```sql
CREATE DATABASE springbites;
```

### 3. Run application

Using Maven:

```bash
mvn spring-boot:run
```

Application runs at:

```
http://localhost:8089
```

---

## Future Improvements

* Refresh token support
* Swagger documentation
* Redis caching
* Payment integration
* Docker support
* Rate limiting

---

## Contributors

* Lalit — Security & Platform (Login and Register)
* Aditi — Cart & Orders
* Ayushi- Catalog Module 


