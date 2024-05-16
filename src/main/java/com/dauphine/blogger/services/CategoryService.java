package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.impl.exceptions.CategoryNotFoundByNameException;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> getAll();

    List<Category> getAllByName(String name);

    Category getById(UUID id) throws CategoryNotFoundByNameException;

    Category create(String name);

    Category updateName(UUID id, String newName) throws CategoryNotFoundByNameException;

    boolean deleteById(UUID id);
}
