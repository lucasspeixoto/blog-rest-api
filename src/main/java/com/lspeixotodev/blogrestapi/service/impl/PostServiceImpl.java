package com.lspeixotodev.blogrestapi.service.impl;

import com.lspeixotodev.blogrestapi.entity.Category;
import com.lspeixotodev.blogrestapi.entity.Post;
import com.lspeixotodev.blogrestapi.exception.ResourceNotFoundException;
import com.lspeixotodev.blogrestapi.mappers.BlogMapper;
import com.lspeixotodev.blogrestapi.dto.PostDTO;
import com.lspeixotodev.blogrestapi.dto.PostResponse;
import com.lspeixotodev.blogrestapi.repository.CategoryRepository;
import com.lspeixotodev.blogrestapi.repository.PostRepository;
import com.lspeixotodev.blogrestapi.service.PostService;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        Category category = this.categoryRepository.findById(postDTO.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", postDTO.getCategoryId())
        );

        Post post = this.convertDTOToEntity(postDTO);

        post.setCategory(category);

        Post newPost = this.postRepository.save(post);

        return this.convertEntityToDTO(newPost);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(
                pageNo,
                pageSize,
                sort
        );

        Page<Post> pageablePosts = this.postRepository.findAll(pageable);

        List<Post> pageablePostsContent = pageablePosts.getContent();

        List<PostDTO> content = convertEntitiesToDTOs(pageablePostsContent);

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(content);
        postResponse.setPageNo(pageablePosts.getNumber());
        postResponse.setPageSize(pageablePosts.getSize());
        postResponse.setTotalElements(pageablePosts.getTotalElements());
        postResponse.setTotalPages(pageablePosts.getTotalPages());
        postResponse.setLast(pageablePosts.isLast());

        return postResponse;

    }

    @Override
    public PostDTO getPostById(Long id) {

        Post selectedPost = this.postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );

        return convertEntityToDTO(selectedPost);
    }

    @Override
    public PostDTO updatePost(PostDTO postDto, Long id) {

        Category category = this.categoryRepository.findById(postDto.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId())
        );

        Post entity = this.postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );

        entity.setTitle(postDto.getTitle());
        entity.setDescription(postDto.getDescription());
        entity.setContent(postDto.getContent());
        entity.setCategory(category);

        Post updatedPost = this.postRepository.save(entity);

        return convertEntityToDTO(updatedPost);
    }

    @Override
    public PostDTO deletePost(Long id) {
        Post entity = this.postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );

        this.postRepository.delete(entity);

        return convertEntityToDTO(entity);
    }

    @Override
    public List<PostDTO> getPostsByCategoryId(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        List<Post> posts = this.postRepository.findPostsByCategoryId(categoryId);

        if(posts.isEmpty()) {
            throw new ResourceNotFoundException("Post", "category id", categoryId);
        }

        return this.convertEntitiesToDTOs(posts);
    }

    //! Mapper methods ------------------------------------------
    private PostDTO convertEntityToDTO(Post entity) {
        return BlogMapper.parseObject(entity, PostDTO.class, modelMapper);
    }

    private Post convertDTOToEntity(PostDTO postDTO) {
        return BlogMapper.parseObject(postDTO, Post.class, modelMapper);
    }

    private List<PostDTO> convertEntitiesToDTOs(List<Post> entities) {
        return BlogMapper.parseListObjects(entities, PostDTO.class, modelMapper);
    }

    private List<Post> convertDTOsToEntities(List<PostDTO> postDTOs) {
        return BlogMapper.parseListObjects(postDTOs, Post.class, modelMapper);
    }

    //! ------------------------------------------ Mapper methods
}


