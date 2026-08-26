package com.nordea.demo.helloworld;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1. Unit Testing GreetingController using RestTestClient.bindToController(...)
 * Runs in pure isolation without loading any Spring application context.
 */
public class GreetingControllerTest {

    private RestTestClient client;

    @BeforeEach
    void setup() {
        GreetingService greetingService = new GreetingService();
        GreetingController greetingController = new GreetingController(greetingService);
        // Bind RestTestClient directly to the controller instance
        this.client = RestTestClient.bindToController(greetingController).build();
    }

    @Test
    @DisplayName("GET / should return default Hello, World! with record deserialization")
    void testRootEndpoint() {
        var expected = new Greeting("Hello, World!", "World");

        client.get()
                .uri("/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Greeting.class)
                .isEqualTo(expected)
                .value(greeting -> {
                    assertEquals("Hello, World!", greeting.message());
                    assertEquals("World", greeting.recipient());
                });
    }

    @Test
    @DisplayName("GET /hello with default query parameter should return Hello, World!")
    void testHelloDefault() {
        client.get()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Greeting.class)
                .value(greeting -> {
                    assertEquals("Hello, World!", greeting.message());
                    assertEquals("World", greeting.recipient());
                });
    }

    @Test
    @DisplayName("GET /hello?name=Alice should return personalized greeting")
    void testHelloWithQueryParam() {
        client.get()
                .uri("/hello?name=Alice")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Greeting.class)
                .value(greeting -> {
                    assertEquals("Hello, Alice!", greeting.message());
                    assertEquals("Alice", greeting.recipient());
                });
    }

    @Test
    @DisplayName("GET /hello/Bob should return personalized greeting via path variable")
    void testHelloWithPathVariable() {
        client.get()
                .uri("/hello/Bob")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Greeting.class)
                .value(greeting -> {
                    assertEquals("Hello, Bob!", greeting.message());
                    assertEquals("Bob", greeting.recipient());
                });
    }

    @Test
    @DisplayName("GET /hello/Charlie with fluent JsonPath assertions")
    void testHelloJsonPath() {
        client.get()
                .uri("/hello/Charlie")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.message").isEqualTo("Hello, Charlie!")
                .jsonPath("$.recipient").isEqualTo("Charlie");
    }

    @Test
    @DisplayName("GET /hello/Developer with AssertJ assertions")
    void testHelloAssertJ() {
        client.get()
                .uri("/hello/Developer")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Greeting.class)
                .value(greeting -> {
                    assertThat(greeting.message()).isEqualTo("Hello, Developer!");
                    assertThat(greeting.recipient()).isEqualTo("Developer");
                });
    }
}
