# Crud Products API Rest

CRUD made with Java Spring Boot with a layered architecture. It has exception handling and data validation. It uses MySQL as database.

## Getting Started

### Prerequisites

- Java JDK 17 or higher version.
- Apache Maven 3.9.6 or higher version.
- MySQL.

### Clone the Repository

### Configure Database

In your `application.properties` put the necessary credentials for the MySQL connection.

```
spring.datasource.url=jdbc:mysql://localhost/YOUR_SCHEMA?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrival=true
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

Not is necessary create the `products` table, because the file `src/main/resources/schema.sql` already does it when you run app for the first time.

## Usage

### Run Locally

You can run the app by executing the main class `src/main/java/CrudProductsApplication.java`

### Endpoints API

#### Create a new product

- Method: `POST`
- URL: `/api/products`

#### Get All Products

- Method: `GET`
- URL: `/api/products`

#### Get Product By Id

- Method : `GET`
- URL: `/api/products/${id}`

#### Update Product By Id

- Method: `PUT`
- URL: `/api/products/${id}`

#### Remove Product By ID

- Method: `DELETE`
- URL: `/api/products/${id}`
