package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.impl.exceptions.CategoryNotFoundByNameException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Category> getAllByName(String name) {
        return repository.findAllByName(name);
    }

    @Override
    public Category getById(UUID id) throws CategoryNotFoundByNameException {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundByNameException(id));
    }

    @Override
    public Category create(String name) {
        Category category = new Category(UUID.randomUUID(), name);
        return repository.save(category);
    }

    @Override
    public Category updateName(UUID id, String newName) throws CategoryNotFoundByNameException {
        Category category = getById(id);
        if (category == null) return null;
        category.setName(newName);
        return repository.save(category);
    }

    @Override
    public boolean deleteById(UUID id) {
        repository.deleteById(id);
        return true;
    }
}
