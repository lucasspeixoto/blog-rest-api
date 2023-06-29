package com.lspeixotodev.blogrestapi.controller;

import com.lspeixotodev.blogrestapi.dto.CommentDTO;
import com.lspeixotodev.blogrestapi.service.CommentService;
import com.lspeixotodev.blogrestapi.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/posts", produces = MediaType.APPLICATION_JSON)
@Tag(
        name = "CRUD REST APIs for Comment Resource"
)
public class CommentController {

    @Autowired
    private CommentService service;

    @Operation(
            summary = "Create Comment",
            description = "Create Comment REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") Long postId, @Valid @RequestBody CommentDTO commentDTO) {

        return new ResponseEntity<>(service.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all Comments By Post Id",
            description = "Get all Comments By Post Id REST API is used to list all existing comments into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.ok(service.getCommentsByPostId(postId));
    }

    @Operation(
            summary = "Get all Comments By Id",
            description = "Get all Comments By Id REST API is used to list all existing comments into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long id
    ) throws Exception {
        CommentDTO commentDTO = service.getCommentById(postId, id);

        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Update a Comment",
            description = "Update Comment REST API is used to edit an existing comment into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PutMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long id,
            @RequestBody CommentDTO commentDTO
    ) throws Exception {

        CommentDTO updatedCommentDTO = service.updateComment(postId, id, commentDTO);

        return new ResponseEntity<>(updatedCommentDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Comment By Id",
            description = "Delete Comment By Id REST API is used to remove a comment by id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @DeleteMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> deleteComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long id
    ) throws Exception {

        CommentDTO deletedCommentDTO = service.deleteComment(postId, id);

        return new ResponseEntity<>(deletedCommentDTO, HttpStatus.OK);
    }

}
