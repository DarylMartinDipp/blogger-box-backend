package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.ElementRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(
        name = "Hello World! API",
        description = "My first Hello World! endpoints."
)
public class HelloWorldController {
    @GetMapping("hello_world")
    @Operation(
            summary = "Hello World! endpoint",
            description = "Return 'Hello World!'."
    )
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("hello")
    @Operation(
            summary = "Hello by name in parameters endpoint",
            description = "Return 'Hello {name}!' by requested parameter."
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
            description = "Return 'Hello {name}!' by path variable."
    )
    public String hello(
            @Parameter(description = "Name to greet")
            @PathVariable String name
    ) {
        return "Hello " + name + "!";
    }

    @PostMapping("/elements")
    @Operation(
            summary = "Create a new post",
            description = "Create a new post with a title and a description."
    )
    public String create(@RequestBody ElementRequest body) {
        // TODO later, implement persistance layer
        //  INSERT INTO ... (title, description) VALUES (${title}, ${description});
        return "Create new element with title '%s' and description '%s'."
                .formatted(body.getTitle(), body.getDescription());
    }

    @PutMapping("/element/{id}")
    @Operation(
            summary = "Update a post",
            description = "Update a post according to the id."
    )
    public String update(@PathVariable Integer id, @RequestBody ElementRequest body) {
        // TODO later, implement persistance layer
        //  UPDATE ... SET title = ${title}, description = ${description}) WHERE id = ${id}
        return "Update element '%s' with title '%s' and description '%s'."
                .formatted(id, body.getTitle(), body.getDescription());
    }

    @PatchMapping("/element/{id}/description")
    @Operation(
            summary = "Update the description of a post",
            description = "Update the description of a post according to the id."
    )
    public String patch(@PathVariable Integer id, @RequestBody String description) {
        // TODO later, implement persistance layer
        //  UPDATE ... SET description = ${description}) WHERE id = ${id}
        return "Patch element '%s' with description '%s'."
                .formatted(id, description);
    }

    @DeleteMapping("/element/{id}")
    @Operation(
            summary = "Delete a post",
            description = "Delete a post according to the id."
    )
    public String delete(@PathVariable Integer id) {
        // TODO later, implement persistance layer
        //  DELETE ... WHERE id = ${id}
        return "Delete element '%s'.".formatted(id);
    }
}
