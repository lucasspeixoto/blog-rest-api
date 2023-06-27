package com.lspeixotodev.blogrestapi.controller;

import com.lspeixotodev.blogrestapi.dto.PostDTO;
import com.lspeixotodev.blogrestapi.dto.PostResponse;
import com.lspeixotodev.blogrestapi.service.PostService;
import com.lspeixotodev.blogrestapi.utils.AppConstants;
import com.lspeixotodev.blogrestapi.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", produces = MediaType.APPLICATION_JSON)
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {

    @Autowired
    private PostService service;

    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {

        return new ResponseEntity<>(service.createPost(postDTO), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all Posts",
            description = "Get all Posts REST API is used to list all existing posts into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir

    ) {

        return service.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @Operation(
            summary = "Get Post By Id",
            description = "Get Post By Id REST API is used to get a post by id into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") Long id) throws Exception {
        return ResponseEntity.ok(service.getPostById(id));
    }

    @Operation(
            summary = "Get Post By Category Id",
            description = "Get Post By Category Id REST API is used to get a post by id into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDTO>> getPostsByCategoryId(@PathVariable(name = "id") Long id) throws Exception {
        return ResponseEntity.ok(service.getPostsByCategoryId(id));
    }

    @Operation(
            summary = "Update a Post",
            description = "Update Post REST API is used to edit an existing post into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable(name = "id") Long id) {

        PostDTO responsePostDTO = service.updatePost(postDTO, id);

        return new ResponseEntity<>(responsePostDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Post By Id",
            description = "Delete Post By Id REST API is used to remove a post by id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable(name = "id") Long id) {

        PostDTO responsePostDTO = service.deletePost(id);

        return new ResponseEntity<>(responsePostDTO, HttpStatus.OK);
    }
}
