package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.impl.exceptions.CategoryNotFoundByNameException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> getAll(@RequestParam String name) {
        return name == null || name.isBlank()
                ? service.getAll()
                : service.getAllByName(name);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> retrieveCategoryById(@PathVariable UUID id) {
        try {
            final Category category = service.getById(id);
            return ResponseEntity.ok(category);
        } catch (CategoryNotFoundByNameException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category request) {
        final Category category = service.create(request.getName());
        return ResponseEntity
                .created(URI.create("v1/categories/" + category.getId()))
                .body(category);
    }

    @PutMapping("{id}")
    public Category updateCategory(@PathVariable UUID id,
                                   @RequestBody String name) throws CategoryNotFoundByNameException {
        return service.updateName(id, name);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable UUID id) {
        service.deleteById(id);
    }
}
