# OrderApp

## Why was this application created and which frameworks/libraries I used for this project?
The application was developed for the purpose of passing the "Zajavka" bootcamp project.
To designed this application I have used: 
- Java 17,
- HTML, CSS,
- Gradle
- Lombok,
- Spring boot,
- Spring Data JPA,
- Spring Web MVC,
- Tomcat,
- Thymeleaf,
- Spring security,
- PostgreSQL,
- Flyway,
- Mockito,
- Docker.

## Functionality for restaurant owner
1. Log into the application,
2. Define menus for the restaurants he has previously created,
3. Access to the orders placed by the client at a specific restaurant,
4. Adding restaurant and selecting streets in which delivery will be available,
5. Updating of the order status,
6. Ability to delete previously created restaurants


## Functionality for client
1. Log into the application,
2. Find restaurant by street name, which are connected with restaurant (streets define the delivery area),
3. Place an order for selected restaurant,
4. Look into orders, which client made in the process,
5. Cancel order.


## Installation
1. Clone repository into your desktop.
2. To open correctly this application, you need Docker and its containers. Run container by using:
```
./gradlew clean build -x test

docker compose up -d
```

3. To use this application, you have to open and put following address:

```
http://localhost:8190/order-app/login
```

4. Log into app, by filling login form with prepared testing data:

```
For client:
username: test_client , password: test

For restaurant owner: 
username: test_owner , password: test
```

## ERD Diagram
Below the ERD Diagram was shown:
![](src/main/resources/diagram.png)

### What are my plans for the future ?
To add REST API and SwaggerUI,
