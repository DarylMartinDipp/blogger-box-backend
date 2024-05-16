package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query("""
        SELECT p
        FROM Post p
        WHERE p.category.id=:categoryID
    """)
    List<Post> getAllByCategoryId(@Param("categoryID") UUID categoryID);
}
