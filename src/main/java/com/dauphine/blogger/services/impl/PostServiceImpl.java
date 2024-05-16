package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostRepository;
import com.dauphine.blogger.services.PostService;

import java.util.List;
import java.util.UUID;

public class PostServiceImpl implements PostService {
    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return repository.getAllByCategoryId(categoryId);
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public Post getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Post create(String title, String content, Category category) {
        Post post = new Post(title, content, category);
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = getById(id);
        if (post == null) return null;
        post.setTitle(title);
        post.setContent(content);
        return repository.save(post);
    }

    @Override
    public boolean deleteById(UUID id) {
        repository.deleteById(id);
        return true;
    }
}
