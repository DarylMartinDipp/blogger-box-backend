package com.dauphine.blogger.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(
        name = "Hello World! API",
        description = "My first Hello World! endpoints"
)
public class HelloWorldController {
    @GetMapping("hello_world")
    @Operation(
            summary = "Hello World! endpoint",
            description = "Return 'Hello World!'"
    )
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("hello")
    @Operation(
            summary = "Hello by name in parameters endpoint",
            description = "Return 'Hello {name}!' by requested parameter"
    )
    public String helloByName(
            @Parameter(description = "Name to greet")
            @RequestParam String name
    ) {
        return "Hello " + name + "!";
    }

    @GetMapping("hello/{name}")
    @Operation(
            summary = "Hello by name endpoint",
            description = "Return 'Hello {name}!' by path variable"
    )
    public String hello(
            @Parameter(description = "Name to greet")
            @PathVariable String name
    ) {
        return "Hello " + name + "!";
    }
}
