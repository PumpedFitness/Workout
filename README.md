# Workout API

A Kotlin-based API framework for building workout and fitness applications. This framework provides a solid foundation with user management, authentication, and infrastructure setup, allowing you to focus on building your specific workout application features.

## Overview

Workout API is built on the KTOR framework and provides:

- User management with JWT authentication
- Database integration with MariaDB and migrations
- Caching with Redis
- Messaging with RabbitMQ
- API documentation with OpenAPI and Swagger
- Comprehensive testing infrastructure
- Docker-based deployment

## Getting Started

### Prerequisites

- JDK 21 or higher
- Docker and Docker Compose (for running infrastructure services)
- Gradle (included as wrapper)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Workout.git
   cd Workout
   ```

2. Copy the environment template:
   ```bash
   cp .env.template .env
   ```

3. Edit the `.env` file with your configuration values:
   ```
   # Database
   BB_DB_ROOT_PASSWORD=your_root_password
   BB_DB_USER=your_db_user
   BB_DB_PASSWORD=your_db_password
   BB_DB_DATABASE=workout
   BB_DB_PORT=3306
   
   # Redis
   BB_REDIS_PORT=6379
   
   # RabbitMQ
   BB_RABBITMQ_USER=your_rabbitmq_user
   BB_RABBITMQ_PASSWORD=your_rabbitmq_password
   BB_RABBITMQ_PORT=5672
   BB_RABBITMQ_HTTP_PORT=15672
   ```

4. Start the infrastructure services:
   ```bash
   docker-compose up -d
   ```

5. Run the application:
   ```bash
   ./gradlew run
   ```

The application will start on http://localhost:8080.

## API Endpoints

### Status Endpoint

- **GET /status**: Returns "OK" if the service is running
  ```bash
  curl http://localhost:8080/status
  ```

### Adding Custom Endpoints

To add your own endpoints, create a new route file in the `org.pumped.route` package:

```kotlin
fun Route.yourCustomRouting() {
    get("/your-endpoint") {
        call.respondText("Your response")
    }
    
    post("/your-post-endpoint") {
        // Handle POST request
    }
}
```

Then register your routes in the `Router.kt` file:

```kotlin
fun Application.configureRoutes() {
    routing {
        statusRouting()
        yourCustomRouting() // Add your custom routes
    }
}
```

## Creating a Service

To create a new service using this framework, extend the `MiniService` class:

```kotlin
class MyWorkoutService : MiniService("my-workout-service") {
    override fun Application.onBoot() {
        // Perform service-specific initialization here
    }
    
    override fun onShutdown() {
        // Cleanup resources when the service shuts down
    }
}

fun main() {
    MyWorkoutService() // This will start the server
}
```

## Database Migrations

The project uses Flyway for database migrations. Migration files are located in `src/main/resources/db/migration`.

To create a new migration:

1. Create a new SQL file in the migrations directory with the naming convention `V{timestamp}__{description}.sql`
2. Write your SQL migration
3. Run the migration with:
   ```bash
   ./gradlew flywayMigrate
   ```

## Configuration

### Environment Variables

The application uses environment variables for configuration. These can be provided in a `.env` file or as system environment variables.

Key environment variables:

| Variable | Description | Default |
|----------|-------------|---------|
| BB_DB_HOST | Database host | localhost |
| BB_DB_PORT | Database port | 3306 |
| BB_DB_USER | Database user | |
| BB_DB_PASSWORD | Database password | |
| BB_DB_DATABASE | Database name | workout |
| BB_REDIS_HOST | Redis host | localhost |
| BB_REDIS_PORT | Redis port | 6379 |
| BB_RABBITMQ_HOST | RabbitMQ host | localhost |
| BB_RABBITMQ_PORT | RabbitMQ port | 5672 |
| BB_RABBITMQ_USER | RabbitMQ user | |
| BB_RABBITMQ_PASSWORD | RabbitMQ password | |

## Testing

The project includes support for unit tests and integration tests.

To run unit tests:
```bash
./gradlew test
```

To run integration tests:
```bash
./gradlew integrationTest
```

## Building and Deployment

### Building a JAR

```bash
./gradlew buildFatJar
```

The JAR will be created in `build/libs/`.

### Building a Docker Image

```bash
export DOCKER_IMAGE=your-image-name:tag
./gradlew jib
```

### Running with Docker

```bash
docker run -p 8080:8080 your-image-name:tag
```

## Documentation

API documentation is available via Swagger UI at http://localhost:8080/swagger when the application is running.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.