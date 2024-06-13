package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;

import java.util.List;
import java.util.UUID;

public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    private final CategoryService categoryService;

    public PostServiceImpl(PostRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) throws CategoryNotFoundByIdException {
        categoryService.getById(categoryId);
        return repository.getAllByCategoryId(categoryId);
    }

    @Override
    public Post getById(UUID id) throws PostNotFoundByIdException {
        return repository.findById(id).orElseThrow(() -> new PostNotFoundByIdException(id));
    }

    @Override
    public Post create(String title, String content, UUID categoryId) throws CategoryNotFoundByIdException {
        Category category = categoryService.getById(categoryId);
        Post post = new Post(title, content, category);
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content, UUID categoryId)
            throws PostNotFoundByIdException, CategoryNotFoundByIdException {
        Post post = getById(id);
        Category category = categoryService.getById(categoryId);
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        return repository.save(post);
    }

    @Override
    public void deleteById(UUID id) throws PostNotFoundByIdException {
        getById(id);
        repository.deleteById(id);
    }
}
