# Sales System
### This is the backend code of a small sales system.

## Usage Guide:
You can read the API Documentation from [here](https://documenter.getpostman.com/view/11964377/UVkjwHiw).

### Clients:

You can create, update and retrieve the clients by using the designated APIs.

### Products:

In order to create a product we have to attach it to a category by providing the category ID in the request.

### Sales:
A sale object should consist of one transaction at least, where a transaction is defined as an object which holds the purchased product with the specified quantity and price.


## Prerequisites: 

In order to connect to the database the application.properties file should be filled with the database information.