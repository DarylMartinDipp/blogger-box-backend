package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.ElementRequest;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(
        name = "Post Controller API",
        description = "Post-related endpoints."
)
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/elements")
    @Operation(
            summary = "Create a new post endpoint",
            description = "Create a new post with a title and a description."
    )
    public ResponseEntity<Post> create(@RequestBody ElementRequest postToCreate) {
        try {
            Post post = postService.create(
                    postToCreate.getTitle(), postToCreate.getContent(), postToCreate.getCategory_id()
            );
            return ResponseEntity.ok(post);
        } catch (CategoryNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/element/{id}")
    @Operation(
            summary = "Update a post endpoint",
            description = "Update a post according to the id."
    )
    public ResponseEntity<Post> update(@PathVariable UUID id, @RequestBody ElementRequest postToUpdate) {
        try {
            Post post = postService.update(
                    id, postToUpdate.getTitle(), postToUpdate.getContent(), postToUpdate.getCategory_id()
            );
            return ResponseEntity.ok(post);
        } catch (CategoryNotFoundByIdException | PostNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/element/{id}")
    @Operation(
            summary = "Delete a post endpoint",
            description = "Delete an existing post according to the id."
    )
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            postService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (PostNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}