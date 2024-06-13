package com.dauphine.blogger.services;

import com.dauphine.blogger.exceptions.CategoryAlreadyExistsException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> getAll();

    List<Category> getAllByName(String name);

    Category getById(UUID id) throws CategoryNotFoundByIdException;

    Category create(String name) throws CategoryAlreadyExistsException;

    Category updateName(UUID id, String newName) throws CategoryNotFoundByIdException, CategoryAlreadyExistsException;

    void deleteById(UUID id) throws CategoryNotFoundByIdException;
}
