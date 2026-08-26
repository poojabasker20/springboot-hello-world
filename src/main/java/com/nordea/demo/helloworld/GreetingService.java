package com.nordea.demo.helloworld;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public Greeting getGreeting(String name) {
        return Greeting.of(name);
    }

    public Greeting getDefaultGreeting() {
        return Greeting.of("World");
    }
}
