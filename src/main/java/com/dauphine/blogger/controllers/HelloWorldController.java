package com.dauphine.blogger.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("hello_world")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("hello_name")
    public String helloByName(@RequestParam String name) {
        return "Hello " + name + "!";
    }
}
