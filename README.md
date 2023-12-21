<h4> wishlistapp </h4>

<hr>

<h3 align="center">
    Wishlist
    <br>
    Rest API to manager wishlist
    <br><br>
</h3>

<hr>

## 🔖 About

The goal is for you to develop an HTTP service that resolves the functionality of the customer's Wishlist


---

## 👨‍💻 Author

* [Fabrício Raphael](https://www.linkedin.com/in/fabricioraphael/)

---

## 🚀 Tecnologies

- Backend:
    - [Java 17](https://openjdk.org/projects/jdk/17/)
    - [Spring Boot 3.2.0](https://spring.io/blog/2023/11/23/spring-boot-3-2-0-available-now)
    - [Apache Maven](https://maven.apache.org/)
- Design
  - [DDD - Domain Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design)
  - [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- Database:
    - [MongoDB](https://www.mongodb.com/)
- Tools
    - [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/)
    - [Postman](http://www.postman.com/downloads/)
    - [Git](https://git-scm.com/downloads)
    - [Docker](https://docs.docker.com/desktop/install/mac-install/)

---

## ⤵ Quick Setup Instructions

Install prerequisites:
- Docker (Docker-Compose)

--- 

<br>

- Step 1: Clone the repository:
  ```bash
  $ git clone https://github.com/fabricioraphael/wishlistapp.git
  ```

<br>

- Step 2: Accessing the docker directory:
  - Step 2.1: access docker-local dir
  ```bash
  $ cd wishlistapp/docker-local/
  ```
  - Step 2.2: run services (database and server)
  ```bash
  $ docker-compose up -d
  ```

---
## Usage:
  - Import Wishlist.postman_collection.json in Postman
  - See documentation in [swagger](http://127.0.0.1:8080/swagger-ui/index.html) _(with the server running)_
  - Or with CUrl _(with the server running)_
    - Add a Product to Customer's Wishlist
      ```bash
      $ curl --location 'http://127.0.0.1:8080/wishlists/add-product' \
      --header 'Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiY3VzdG9tZXJJZCI6IjQxMjQ2MzYyMzkxMjQzZTdiMWJkZjkxNGU5Y2YzNTg0IiwiaWF0IjoxNTE2MjM5MDIyfQ.teBWXEUWun0HMduclx4d8taMU4ziY4YV_6xwDB99g-g' \
      --header 'Content-Type: application/json' \
      --data '{
      "productId": "87546362391243e7b1bdf914e9ca9d7e"
      }'
      ```
    
    - Retrive All Products From  Customer's Wishlist
      ```bash
      $ curl --location 'http://127.0.0.1:8080/wishlists/products' \
      --header 'Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiY3VzdG9tZXJJZCI6IjQxMjQ2MzYyMzkxMjQzZTdiMWJkZjkxNGU5Y2YzNTg0IiwiaWF0IjoxNTE2MjM5MDIyfQ.teBWXEUWun0HMduclx4d8taMU4ziY4YV_6xwDB99g-g'
      ```

    - Retrive a Product from Customer's Wishlist
      ```bash
      $ curl --location 'http://127.0.0.1:8080/wishlists/products/87546362391243e7b1bdf914e9ca9d7e' \
      --header 'Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiY3VzdG9tZXJJZCI6IjQxMjQ2MzYyMzkxMjQzZTdiMWJkZjkxNGU5Y2YzNTg0IiwiaWF0IjoxNTE2MjM5MDIyfQ.teBWXEUWun0HMduclx4d8taMU4ziY4YV_6xwDB99g-g'
      ```

    - Delete a Product from Customer's Wishlist
      ```bash
      $ curl --location --request DELETE 'http://127.0.0.1:8080/wishlists/products/87546362391243e7b1bdf914e9ca9d7e' \
      --header 'Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiY3VzdG9tZXJJZCI6IjQxMjQ2MzYyMzkxMjQzZTdiMWJkZjkxNGU5Y2YzNTg0IiwiaWF0IjoxNTE2MjM5MDIyfQ.teBWXEUWun0HMduclx4d8taMU4ziY4YV_6xwDB99g-g'
      ```

---
## Requirements:

###### One of the most interesting functionalities in an e-commerce platform is the Wishlist, or the list of desired items. In an e-commerce platform, a customer can search for products or access the product details page. On both screens, they can select preferred products and store them in their Wishlist. At any time, the customer can view their complete Wishlist, displaying all the products they have selected on a single screen.

- Add a product to the customer's Wishlist.
- Remove a product from the customer's Wishlist.
- Retrieve all products from the customer's Wishlist.
- Check if a specific product is in the customer's Wishlist.

---

## 📝 License

[MIT License](https://opensource.org/license/mit/) 

---