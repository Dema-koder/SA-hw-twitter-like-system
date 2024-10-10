# Twitter-like System

This project is a simple implementation of a microservice-based system similar to the like feature in Twitter. It consists of three microservices: 

1. **User Service** - Manages user registration and provides user information.
2. **Message Service** - Manages messages and provides message information.
3. **Like Service** - Manages the liking of messages by users and tracks the number of likes per message.

## Project Structure

```bash
.
├── docker-compose.yaml        # Docker Compose configuration to run all services
├── like-service               # Like Service
│   ├── Dockerfile             # Dockerfile for Like Service
│   ├── pom.xml                # Maven configuration for Like Service
│   └── src
│       └── main
│           ├── java
│           │   └── com.example
│           │       ├── Main.java                   # Main entry point
│           │       ├── configuration
│           │       │   └── RestTemplateConfig.java # Configuration for RestTemplate
│           │       └── controller
│           │           └── LikesController.java    # REST controller for managing likes
│           └── resources
│               └── application.properties          # Application properties for Like Service
├── message-service            # Message Service
│   ├── Dockerfile             # Dockerfile for Message Service
│   ├── pom.xml                # Maven configuration for Message Service
│   └── src
│       └── main
│           ├── java
│           │   └── com.example
│           │       ├── Main.java                   # Main entry point
│           │       ├── configuration
│           │       │   └── RestTemplateConfig.java # Configuration for RestTemplate
│           │       ├── controller
│           │       │   └── MessageController.java  # REST controller for managing messages
│           │       ├── model
│           │       │   └── Message.java            # Message model class
│           │       └── service
│           │           ├── MessageService.java     # Service class for messages
│           │           └── UserServiceClient.java  # Client to interact with User Service
│           └── resources
│               └── application.properties          # Application properties for Message Service
├── user-service               # User Service
│   ├── Dockerfile             # Dockerfile for User Service
│   ├── pom.xml                # Maven configuration for User Service
│   └── src
│       └── main
│           ├── java
│           │   └── com.example
│           │       ├── Main.java                   # Main entry point
│           │       ├── controller
│           │       │   └── UserController.java     # REST controller for managing users
│           │       └── model
│           │           └── User.java               # User model class
│           └── resources
│               └── application.properties          # Application properties for User Service
├── pom.xml                    # Parent pom.xml for multi-module Maven project
```

## Microservices

### 1. **User Service**
- **Purpose**: Handles user registration and retrieval.
- **Endpoints**:
  - `POST /users/register?username={username}`: Register a new user.
  - `GET /users/{username}`: Get user information.
  - `GET /users/getAll`: Get information about all users.

### 2. **Message Service**
- **Purpose**: Manages the creation and retrieval of messages.
- **Endpoints**:
  - `POST /messages/post?username={}&content={}`: Create a new message.
  - `GET /messages/{messageId}`: Checking whether there is a message with this id
  - `GET /messages/getAll`: Get the latest 10 messages.

### 3. **Like Service**
- **Purpose**: Manages user likes on messages and counts likes for each message.
- **Endpoints**:
  - `POST /likes/{messageId}?username={username}`: Like a message.
  - `GET /likes/{messageId}`: Get the list of users who liked a message.

## Prerequisites

- Docker
- Docker Compose
- Maven

## Running the Project

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd twitter-like-system
   ```

2. **Build and run the services using Docker Compose**:
   ```bash
   docker-compose up --build
   ```

3. **Access the services**:
   - **User Service**: `http://localhost:8080`
   - **Message Service**: `http://localhost:8081`
   - **Like Service**: `http://localhost:8082`

## Usage

### Example Requests
  ```bash
  http://localhost:8080/users/register?username=john
  http://localhost:8080/users/getAll
  http://localhost:8081/messages/post?username=john&content=Hello, World!
  http://localhost:8081/messages/getAll
  http://localhost:8082/likes/0?username=john
  http://localhost:8082/likes/0
  ```

## Configuration

Each microservice has its own `application.properties` file where you can configure server ports.

---

This README provides an overview of the microservices, steps to run the project, and examples of how to interact with the services.
