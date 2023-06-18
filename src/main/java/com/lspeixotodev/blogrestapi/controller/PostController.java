package com.lspeixotodev.blogrestapi.controller;

import com.lspeixotodev.blogrestapi.payload.PostDTO;
import com.lspeixotodev.blogrestapi.payload.PostResponse;
import com.lspeixotodev.blogrestapi.service.PostService;
import com.lspeixotodev.blogrestapi.utils.AppConstants;
import com.lspeixotodev.blogrestapi.utils.MediaType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/posts", produces = MediaType.APPLICATION_JSON)
public class PostController {

    @Autowired
    private PostService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {

        return new ResponseEntity<>(service.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir

    ) {

        return service.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") Long id) throws Exception {
        return ResponseEntity.ok(service.getPostById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable(name = "id") Long id) {

        PostDTO responsePostDTO = service.updatePost(postDTO, id);

        return new ResponseEntity<>(responsePostDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable(name = "id") Long id) {

        PostDTO responsePostDTO = service.deletePost(id);

        return new ResponseEntity<>(responsePostDTO, HttpStatus.OK);
    }
}
