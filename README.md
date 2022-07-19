# Codecool-Shop
[![Actions](https://github.com/gothinkster/spring-boot-realworld-example-app/workflows/Java%20CI/badge.svg)](https://github.com/gothinkster/spring-boot-realworld-example-app/actions)
![anotherLogo2](https://user-images.githubusercontent.com/86559678/179210570-10d9304f-c2ca-4efa-b204-265eba832822.jpeg)
> ### Codecool-Shop is an online shop for customers to buy smartphones, and gagdes.
Codecool-Shop was created to get familiarised with jetty technology combining with HTML, CSS and javascript.

## General functionality
- CRUD Operation
- User can register and login
- User can decrease or increase the products directly from cart
- The products can be sorted both by the categoty and supplier
- Products can be found by searching by their name

## PostgreSql Schema
![codecoolShop@localhost](https://user-images.githubusercontent.com/86559678/178486179-a7776a00-5d49-4517-ba60-c22511ce4bc4.png)

## Getting started
### Maven Installation
Make sure you have [Maven (Windows)](https://www.educba.com/install-maven/) or [Maven (Linux)](https://www.journaldev.com/33588/install-maven-linux-ubuntu) install.

### Setting up the database
In the resources folder, there is file named `connection.properties`, there you have to change the following values:
- `database` -> new database just created 
- `user` -> personal username
- `password` -> personal password

To create the tables of the database automatically, you have to run `init_db.sql`. Check out the above **PostgreSql Schema**.

### Start the project
To start the project, the command-line is used: `mvn jetty:run`. The port running is: `localhost:8080`.

## Functionality overview


### The general page breakdown:
- Home Page (URL: /)
    - Navbar: Logo, Home, Shop, Contact us, Register, Login.
    - Body: Product cards with title, description, supplier, price and *add card* button
    
- Shopping Cart (URL: /shopping-cart)
    - The user can see the product, the price, the quantity and the total per product
    - As well, the products quantity can be modified by the user and it can also be deleted from the shopping cart
  
- Checkout (URL: /checkout)
    - The website requires from user personal details, billing address as well as shipping address information.

- Payment (URL: /payment)
    - Here, the user needs to introduce the credit card credendials.
 
 - Register/login pages (URL: /register, /login)
    - The user needs to fill the fields: username, email address, password, confirm password on register page.
    - The user login in using the account just created on login page.
    
## Screenshots
![Screenshot from 2022-07-12 15-35-18](https://user-images.githubusercontent.com/86559678/178491840-9af73356-497e-488b-8580-a22a54931178.png)
![Screenshot from 2022-07-12 15-35-33](https://user-images.githubusercontent.com/86559678/178491857-8269a908-bc41-48f8-9a11-c622ac75b299.png)
![Screenshot from 2022-07-12 15-40-48](https://user-images.githubusercontent.com/86559678/178492374-c0ea215b-af50-4298-ae8b-2e08ef2f0dd9.png)
![Screenshot from 2022-07-12 15-33-00](https://user-images.githubusercontent.com/86559678/178492605-b8e576b8-ee00-4329-8049-1ed7faf96665.png)
![Screenshot from 2022-07-12 15-34-01](https://user-images.githubusercontent.com/86559678/178492711-937f17fc-691d-4471-9f3a-99250d861ad6.png)
![Screenshot from 2022-07-12 15-34-09](https://user-images.githubusercontent.com/86559678/178492808-fc77d633-5fff-46d6-82a2-c5b92606916c.png)
![Screenshot from 2022-07-12 15-34-29](https://user-images.githubusercontent.com/86559678/178493024-d4ace649-6e01-4a9a-b31b-5d489ca64e12.png)

