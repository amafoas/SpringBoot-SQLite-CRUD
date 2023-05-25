# About

This is a CRUD (Create, Read, Update, Delete) application built with Spring Boot that uses SQLite for data persistence. It features a single endpoint responsible for managing a user database through a RESTful API. The main idea behind this project is to serve as a template for easily integrating data persistence into new projects.

Feel free to use this project as a starting point or template for your own applications, enabling straightforward integration of data persistence into new projects.

If you have any questions or need further assistance, please let me know.

## Installation and Setup

### Prerequisites

Before running the project, ensure that you have the following prerequisites installed:

- Java Development Kit (JDK) version 17 or higher
- Gradle build tool version 8.1.1 or higher
- SQLite database version 3.32.2 or higher

### Steps
```
  // Clone the repository:
  git clone https://github.com/amafoas/SpringBoot-SQLite-CRUD.git

  // Navigate to the project directory:
  cd SpringBoot-SQLite-CRUD

  // Build the project using Gradle:
  gradle build

  // Run the project:
  gradle bootRun
```
The application should now be up and running at `http://localhost:8080`.

### Tests
If you want to run the test cases to test the `/users` endpoint, use the following command:

```
  gradle test
```

## Usage

To use the `/users` endpoint, you can make HTTP requests to perform various operations on user data. Here are the available endpoints and their corresponding operations:

### User Object

The `User` object represents a user entity and has the following fields:

- `name` (string): Represents the name of the user.
- `email` (string): Represents the email address of the user.

```json
{
  "name": "John Doe",
  "email": "johndoe@example.com"
}
```

### Endpoints

- **Add a User**
  - Endpoint: `POST /users`
  - Request Body: JSON object representing the user data
  - Response: Returns the added user with a status code of 201 (Created) if successful. If an error occurs, it returns a status code of 400 (Bad Request).

- **Get a User**
  - Endpoint: `GET /users/{id}`
  - Path Parameter: `id` represents the unique identifier of the user.
  - Response: Returns the user with the specified `id`.

- **Update a User**
  - Endpoint: `PUT /users/{id}`
  - Path Parameter: `id` represents the unique identifier of the user to be updated.
  - Request Body: JSON object representing the updated user data.
  - Response: Returns the updated user.

- **Delete a User**
  - Endpoint: `DELETE /users/{id}`
  - Path Parameter: `id` represents the unique identifier of the user to be deleted.
  - Response: Returns a status code of 204 (No Content) if the user is successfully deleted. If the user with the specified `id` is not found, it returns a status code of 404 (Not Found).

- **Get All Users**
  - Endpoint: `GET /users`
  - Response: Returns a list of all users.

Make sure to replace `{id}` with the actual identifier of the user when making requests to specific endpoints.

Feel free to adjust and customize the endpoints according to your specific requirements.
