package com.nordea.demo.helloworld;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit Testing GreetingController using Standalone MockMvc
 */
public class GreetingControllerTest {

    private MockMvc mockMvc;
    private GreetingController greetingController;

    @BeforeEach
    void setup() {
        GreetingService greetingService = new GreetingService();
        greetingController = new GreetingController(greetingService);
        mockMvc = MockMvcBuilders.standaloneSetup(greetingController).build();
    }

    @Test
    @DisplayName("GET / should return default Hello, World!")
    void testRootEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, World!"))
                .andExpect(jsonPath("$.recipient").value("World"));
    }

    @Test
    @DisplayName("GET /hello with default param should return Hello, World!")
    void testHelloDefault() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, World!"))
                .andExpect(jsonPath("$.recipient").value("World"));
    }

    @Test
    @DisplayName("GET /hello?name=Alice should return personalized greeting")
    void testHelloWithQueryParam() throws Exception {
        mockMvc.perform(get("/hello?name=Alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Alice!"))
                .andExpect(jsonPath("$.recipient").value("Alice"));
    }

    @Test
    @DisplayName("GET /hello/Bob should return personalized greeting via path variable")
    void testHelloWithPathVariable() throws Exception {
        mockMvc.perform(get("/hello/Bob"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Bob!"))
                .andExpect(jsonPath("$.recipient").value("Bob"));
    }

    @Test
    @DisplayName("Direct unit test invocation with AssertJ")
    void testDirectControllerCall() {
        Greeting greeting = greetingController.hello("Developer");
        assertThat(greeting.message()).isEqualTo("Hello, Developer!");
        assertThat(greeting.recipient()).isEqualTo("Developer");
    }
}
