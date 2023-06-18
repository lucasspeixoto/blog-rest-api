package com.lspeixotodev.blogrestapi.controller;

import com.lspeixotodev.blogrestapi.dto.CommentDTO;
import com.lspeixotodev.blogrestapi.service.CommentService;
import com.lspeixotodev.blogrestapi.utils.MediaType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", produces = MediaType.APPLICATION_JSON)
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") Long postId, @Valid @RequestBody CommentDTO commentDTO) {

        return new ResponseEntity<>(service.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.ok(service.getCommentsByPostId(postId));
    }

    @GetMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long id
    ) throws Exception {
        CommentDTO commentDTO = service.getCommentById(postId, id);

        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long id,
            @RequestBody CommentDTO commentDTO
    ) throws Exception {

        CommentDTO updatedCommentDTO = service.updateComment(postId, id, commentDTO);

        return new ResponseEntity<>(updatedCommentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> deleteComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long id
    ) throws Exception {

        CommentDTO deletedCommentDTO = service.deleteComment(postId, id);

        return new ResponseEntity<>(deletedCommentDTO, HttpStatus.OK);
    }

}
