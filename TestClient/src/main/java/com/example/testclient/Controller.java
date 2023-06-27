package com.example.testclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/greeting")
    public String getGreeting() {
        return "Hello, World!";
    }
}