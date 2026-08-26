package com.nordea.demo.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/")
    public Greeting home() {
        return greetingService.getDefaultGreeting();
    }

    @GetMapping("/hello")
    public Greeting hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return greetingService.getGreeting(name);
    }

    @GetMapping("/hello/{name}")
    public Greeting helloWithName(@PathVariable String name) {
        return greetingService.getGreeting(name);
    }
}
