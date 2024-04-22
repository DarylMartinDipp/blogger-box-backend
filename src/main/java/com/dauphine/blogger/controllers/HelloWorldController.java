package com.dauphine.blogger.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(
        name = "Hello World! API",
        description = "My first Hello World! endpoints."
)
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
        return "Hello " + name + "!";
    }
}
