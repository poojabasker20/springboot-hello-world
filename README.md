# Spring Boot Hello World Service

A modern Spring Boot reference service implementing Hello World REST endpoints and tested across all testing layers.

---

## Endpoints

| HTTP Method | Path | Description | Example Response |
| :--- | :--- | :--- | :--- |
| `GET` | `/` | Default Hello World greeting | `{"message": "Hello, World!", "recipient": "World"}` |
| `GET` | `/hello` | Greeting with optional query parameter `?name=...` | `{"message": "Hello, Alice!", "recipient": "Alice"}` |
| `GET` | `/hello/{name}` | Personalized greeting via path variable | `{"message": "Hello, Bob!", "recipient": "Bob"}` |

---

## Testing with RestTestClient

| Testing Layer | RestTestClient Binding Method | Test Class |
| :--- | :--- | :--- |
| **1. Unit Testing (No Spring Context)** | `RestTestClient.bindToController(controller).build()` | [`GreetingControllerTest.java`](src/test/java/com/nordea/demo/helloworld/GreetingControllerTest.java) |

---

## Running the Tests

To run the complete test suite:

```bash
./mvnw clean test
```

To run the application locally:

```bash
./mvnw spring-boot:run
```
