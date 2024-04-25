package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final List<Category> temporaryCategories;

    public CategoryServiceImpl() {
        this.temporaryCategories = new ArrayList<>();
        this.temporaryCategories.add(new Category(UUID.randomUUID(), "My first category"));
        this.temporaryCategories.add(new Category(UUID.randomUUID(), "My second category"));
        this.temporaryCategories.add(new Category(UUID.randomUUID(), "My third category"));
    }

    @Override
    public List<Category> getAll() {
        return this.temporaryCategories;
    }

    @Override
    public Category getById(UUID id) {
        return this.temporaryCategories.stream()
                .filter(category -> id.equals(category.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Category create(String name) {
        Category category = new Category(UUID.randomUUID(), name);
        temporaryCategories.add(category);
        return category;
    }

    @Override
    public Category updateName(UUID id, String newName) {
        Category category = temporaryCategories.stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst()
                .orElse(null);
        if (category != null)
            category.setName(newName);
        return category;
    }

    @Override
    public void deleteById(UUID id) {
        temporaryCategories.removeIf(category -> id.equals(category.getId()));
    }
}
