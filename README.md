# Chat Application

This application is designed for managing a chat service

## Endpoints

The API exposes the following endpoints:

* `POST /api/v1/users/creation`: Create a new user.
* `POST /api/v1/conversation/creation`: Create a new conversation.
* `POST /api/v1/conversation/{userId}/join/{conversationId}`: User join to a conversation.
* `GET  /api/v1/users/{userId}/conversations`: Retrieve user conversations.
* `POST /api/v1/messages/send`: Send message to a conversation.

For examples, refer to the Postman collection provided.
[Postman collection](postman/chat_service.postman_collection.json)

## Main Frameworks and Libraries

| Feature                      | Library                                         |
|------------------------------|-------------------------------------------------|
| Build tool                   | Gradle                                          |
| HTTP server                  | Spring Boot Starter Web                         | 
| Version control the database | Liquibase                                       |
| Database integration         | Postgres                                        |

## Environment setup

### Prerequisites

Ensure you have the following tools installed:

1. Java 17
2. Docker

### Setup Instructions

1. Download and install [Docker](https://docs.docker.com/get-docker/). We'll need it in order to run
   a Postgres database using Docker.
2. Run: `docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=secret -d postgres`

### Starting the Applications

1. Run chat/src/main/java/com/chat/service/ChatApplication.java to start the API.

### Improvements

Here's a roadmap for enhancing the Chat application:

1. Increase Test Coverage
   * Expand unit test coverage for critical components.
   * Consider adding tests for edge cases and error scenarios.

2. Integration Tests
   * Introduce integration tests to ensure seamless collaboration between modules.
   * Test the interaction of various components in a real-world scenario.

3. Add Diagrams
   * Create visual architecture diagrams to illustrate the system's structure.
   * Use sequence diagrams to showcase the flow of operations.

4. REST Services Validations
   * Strengthen input validations for REST services.
   * Implement robust validation mechanisms to ensure data integrity.

5. Swagger Documentation
   * Integrate Swagger for comprehensive API documentation.
   * Enable developers to understand and interact with the API effortlessly.