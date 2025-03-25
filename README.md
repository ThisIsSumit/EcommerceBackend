
# 🛒 Spring Boot E-Commerce Application 

## Overview

This is a full-fledged **Spring Boot-based E-Commerce Application** built with scalability, modularity, and security in mind. The application supports **role-based authentication**, **product browsing and filtering**, **shopping cart management**, **order placement**, and **address handling**, all integrated with a **PostgreSQL** database. Designed with RESTful best practices and powered by **Spring Security**, it ensures a secure and smooth shopping experience for both users and admins.

---

## 🔐 Authentication & Authorization

- Role-based access control with **Admin** and **User** roles.
- **Spring Security** with **JWT-based authentication**.
- API Endpoints:
  - `POST /api/auth/signin` – Sign in
  - `POST /api/auth/signup` – Register new user
  - `GET /api/auth/username` – Get logged-in user's username
  - `POST /api/auth/signout` – Sign out

---

## 🛍️ Product Management

- Add, update, delete, and retrieve products.
- Search and filter by category or keyword.
- API Endpoints:
  - `POST /api/admin/categories/{categoryId}/product` – Add product
  - `PUT /api/admin/products/{productId}` – Update product
  - `DELETE /api/admin/products/{productId}` – Delete product
  - `PUT /api/products/{productId}/image` – Update product image
  - `GET /api/public/products` – Get all products
  - `GET /api/public/categories/{categoryId}/products` – Get products by category
  - `GET /api/public/products/{keyword}` – Search products by keyword

---

## 🗃️ Category Management

- Add, update, and delete product categories.
- API Endpoints:
  - `GET /api/public/categories` – Get all categories
  - `POST /api/public/categories` – Create new category
  - `PUT /api/public/categories/{categoryId}` – Update category
  - `DELETE /api/public/categories/{categoryId}` – Delete category

---

## 🛒 Cart Management

- Add, update, view, and delete cart items.
- API Endpoints:
  - `POST /api/carts/products/{productId}/quantity/{quantity}` – Add product to cart
  - `GET /api/carts` – Get all carts
  - `GET /api/carts/users/cart` – Get cart for logged-in user
  - `PUT /api/cart/products/{productId}/quantity/{operation}` – Update product quantity in cart (`operation` can be `increment` or `decrement`)
  - `DELETE /api/carts/{cartId}/product/{productId}` – Remove product from cart

---

## 📍 Address Management

- Manage user addresses.
- API Endpoints:
  - `POST /api/addresses` – Add address
  - `GET /api/address` – Get all addresses
  - `GET /api/address/{addressId}` – Get address by ID
  - `GET /api/users/address` – Get user's saved address
  - `PUT /api/addresses/{addressId}` – Update address
  - `DELETE /api/addresses/{addressId}` – Delete address

---

## 📦 Order Management

- Place orders with a selected payment method.
- API Endpoint:
  - `POST /api/order/users/payments/{paymentMethod}` – Place an order

---

## ⚙️ Tech Stack

- **Java + Spring Boot** – Backend development
- **Spring Security + JWT** – Authentication & authorization
- **Spring Data JPA** – ORM for data access
- **PostgreSQL** – Relational database
- **Swagger** – API documentation

---


---

## 📬 Contributions

Feel free to fork the repository, submit pull requests, or open issues. Any feedback or contributions are appreciated!

---

