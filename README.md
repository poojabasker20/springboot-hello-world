# Enterprise Spring Boot Hello World Service with RestTestClient

A modern Spring Boot reference service implementing Hello World REST endpoints and tested using **`RestTestClient`** (Spring Framework 7 / Spring Boot 4) across all testing layers.

---

## 🌟 Endpoints

| HTTP Method | Path | Description | Example Response |
| :--- | :--- | :--- | :--- |
| `GET` | `/` | Default Hello World greeting | `{"message": "Hello, World!", "recipient": "World"}` |
| `GET` | `/hello` | Greeting with optional query parameter `?name=...` | `{"message": "Hello, Alice!", "recipient": "Alice"}` |
| `GET` | `/hello/{name}` | Personalized greeting via path variable | `{"message": "Hello, Bob!", "recipient": "Bob"}` |

---

## 🎯 Testing with RestTestClient

| Testing Layer | RestTestClient Binding Method | Test Class |
| :--- | :--- | :--- |
| **1. Unit Testing (No Spring Context)** | `RestTestClient.bindToController(controller).build()` | [`GreetingControllerTest.java`](src/test/java/com/nordea/demo/helloworld/GreetingControllerTest.java) |
| **2. MVC Slice Testing (`@WebMvcTest`)** | `RestTestClient.bindTo(mockMvc).build()` | [`GreetingControllerMockMvcTest.java`](src/test/java/com/nordea/demo/helloworld/GreetingControllerMockMvcTest.java) |
| **3. Live Server End-to-End Testing** | `RestTestClient.bindToServer().baseUrl("http://localhost:" + port).build()` | [`GreetingControllerServerTest.java`](src/test/java/com/nordea/demo/helloworld/GreetingControllerServerTest.java) |

---

## 🚀 Running the Tests

To run the complete test suite:

```bash
./mvnw clean test
```

To run the application locally:

```bash
./mvnw spring-boot:run
```
