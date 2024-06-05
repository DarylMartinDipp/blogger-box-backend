package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.impl.exceptions.CategoryNotFoundByNameException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/categories")
@Tag(
        name = "Category Controller API",
        description = "Category-related endpoints."
)
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Get all the posts endpoint",
            description = "Return all the posts that are in the database."
    )
    public List<Category> getAll(@RequestParam String name) {
        return name == null || name.isBlank()
                ? service.getAll()
                : service.getAllByName(name);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Retrieve by ID endpoint",
            description = "Return a certain category according to its id."
    )
    public ResponseEntity<Category> retrieveCategoryById(@PathVariable UUID id) {
        try {
            final Category category = service.getById(id);
            return ResponseEntity.ok(category);
        } catch (CategoryNotFoundByNameException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Create a category endpoint",
            description = "Create a new category."
    )
    public ResponseEntity<Category> createCategory(@RequestBody Category request) {
        final Category category = service.create(request.getName());
        return ResponseEntity
                .created(URI.create("v1/categories/" + category.getId()))
                .body(category);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update a category endpoint",
            description = "Update an existing category."
    )
    public Category updateCategory(@PathVariable UUID id,
                                   @RequestBody String name) throws CategoryNotFoundByNameException {
        return service.updateName(id, name);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete a category endpoint",
            description = "Delete an existing category."
    )
    public void deleteCategory(@PathVariable UUID id) {
        service.deleteById(id);
    }
}
