# Event Feedback Analyzer

A simple Spring Boot REST API for creating and viewing events, collecting and submitting feedback, and analyzing sentiment using a Hugging Face AI model

## Features

- Create and list events
- Submit feedback for specific events
- Analyze sentiment automatically using Hugging Face API
- Get summary of feedback sentiments per event
- Error handling
- Swagger UI documentation
- Dockerized application
- Postman collection included

## Technologies Used

- Java 17
- Spring Boot
- RESTful API
- H2 db
- Hugging Face
- Docker
- Swagger
- Postman
- Lombok
- JUnit & Spring Boot Test

## Swagger
http://localhost:8080/swagger-ui/index.html

## Postman Collection
A Postman collection is included in the **docs/** folder
# Running the App

## Configuration
To run the app, you need to configure your Hugging Face API key

1. Copy the example properties file:

   **cp src/main/resources/application.properties.example src/main/resources/application**

2. Open **src/main/resources/application.properties** and replace:
   huggingface.api.key=YOUR_API_KEY_HERE
   with your actual Hugging Face API token
## Using Docker
### Build the image
docker build -t event-feedback-analyzer .

### Run the container
docker run -p 8080:8080 event-feedback-analyzer

# Using Maven
mvn spring-boot:run