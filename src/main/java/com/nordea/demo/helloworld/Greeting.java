package com.nordea.demo.helloworld;

public record Greeting(
    String message,
    String recipient
) {
    public static Greeting of(String recipient) {
        String target = (recipient == null || recipient.isBlank()) ? "World" : recipient.trim();
        return new Greeting("Hello, " + target + "!", target);
    }
}
