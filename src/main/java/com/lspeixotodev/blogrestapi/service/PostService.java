package com.lspeixotodev.blogrestapi.service;

import com.lspeixotodev.blogrestapi.payload.PostDTO;
import com.lspeixotodev.blogrestapi.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO postDto, Long id);

    PostDTO deletePost(Long id);

}
