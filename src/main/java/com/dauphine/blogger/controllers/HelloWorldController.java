package com.dauphine.blogger.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    @GetMapping("hello_world")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("hello")
    public String helloByName(@RequestParam String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name) {
        return "Welcome " + name + "!";
    }
}
