package com.dauphine.blogger;

import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.impl.CategoryServiceImpl;
import com.dauphine.blogger.services.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServiceImplTest {
    private PostRepository postRepository;
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        CategoryServiceImpl categoryService = mock(CategoryServiceImpl.class);
        postService = new PostServiceImpl(postRepository, categoryService);
    }

    @Test
    void shouldReturnPostWhenGetById() throws PostNotFoundByIdException {
        // Arrange
        UUID id = UUID.randomUUID();
        Category category = new Category("Category");
        Post expected = new Post("Title", "Content", category);
        when(postRepository.findById(id)).thenReturn(Optional.of(expected));

        // Act
        Post actual = postService.getById(id);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWhenNotFoundById() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        PostNotFoundByIdException exception = assertThrows(
                PostNotFoundByIdException.class,
                () -> postService.getById(id)
        );

        // Assert
        assertEquals("The post designated by id " + id + " was not found.",
                exception.getMessage());
    }
}
