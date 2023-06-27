package com.lspeixotodev.blogrestapi.repository;

import com.lspeixotodev.blogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


    @Query(nativeQuery = true, value = """
            SELECT *
            FROM posts
            WHERE posts.category_id = :categoryId
            	""")
    List<Post> findPostsByCategoryId(Long categoryId);

}
