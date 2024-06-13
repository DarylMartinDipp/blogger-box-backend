package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<Post> getAll();

    List<Post> getAllByCategoryId(UUID categoryId) throws CategoryNotFoundByIdException;

    Post getById(UUID id) throws PostNotFoundByIdException;

    Post create(String title, String content, UUID category) throws CategoryNotFoundByIdException;

    Post update(UUID id, String title, String content, UUID categoryId) throws PostNotFoundByIdException, CategoryNotFoundByIdException;

    void deleteById(UUID id) throws PostNotFoundByIdException;
}
