package com.lspeixotodev.blogrestapi.repository;

import com.lspeixotodev.blogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
