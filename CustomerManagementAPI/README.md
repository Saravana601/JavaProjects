# Customer Management Web Application

This is a Customer Management web application built with Java Spring Boot and HTML. It allows users to log in, create customers, view customer lists, update customer information, and delete customers.

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [How to Use](#how-to-use)
5. [API Endpoints](#api-endpoints)
6. [Contributing](#contributing)
7. [License](#license)

## Introduction

This web application provides a simple interface for managing customer information. Users can log in and perform CRUD (Create, Read, Update, Delete) operations on customer data.

## Features

- User authentication to access the application.
- Create new customers with various details.
- View a list of customers.
- Update customer information.
- Delete customers from the list.

## Technologies Used

- Java Spring Boot: Back-end development.
- HTML and JavaScript: Front-end interface.
- Spring RestTemplate: For making API requests.
- RESTful API: To manage customer data.
- External API: Used for authentication and token retrieval.

## How to Use

1. Clone the repository to your local machine.
2. Build and run the Spring Boot application.
3. Open a web browser and navigate to the application URL.
4. Log in using your credentials.
5. Create, update, and delete customer information as needed.

## API Endpoints

The application exposes the following API endpoints:

- `POST /authenticate`: User authentication endpoint.
- `POST /create-customer`: Create a new customer.
- `GET /getCustomerList`: Retrieve a list of customers.
- `POST /delete/{uuid}`: Delete a customer by UUID.
- `POST /update/{uuid}`: Update customer information by UUID.

## Contributing

Contributions to this project are welcome. If you have any suggestions or improvements, please feel free to create a pull request or open an issue.

## License

This project is licensed under the [Your License Name](link-to-license).
