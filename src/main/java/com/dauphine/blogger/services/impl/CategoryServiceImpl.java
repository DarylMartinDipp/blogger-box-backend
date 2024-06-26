package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.exceptions.CategoryAlreadyExistsException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
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
    public Category getById(UUID id) throws CategoryNotFoundByIdException {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundByIdException(id));
    }

    @Override
    public Category create(String name) throws CategoryAlreadyExistsException {
        if (!repository.findAllByName(name).isEmpty()) throw new CategoryAlreadyExistsException(name);
        Category category = new Category(UUID.randomUUID(), name);
        return repository.save(category);
    }

    @Override
    public Category updateName(UUID id, String newName)
            throws CategoryNotFoundByIdException, CategoryAlreadyExistsException {
        Category category = getById(id);
        if (!repository.findAllByName(newName).isEmpty()) throw new CategoryAlreadyExistsException(newName);
        category.setName(newName);
        return repository.save(category);
    }

    @Override
    public void deleteById(UUID id) throws CategoryNotFoundByIdException {
        getById(id);
        repository.deleteById(id);
    }
}
