package com.lspeixotodev.blogrestapi.service;

import com.lspeixotodev.blogrestapi.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(Long id, CommentDTO commentDTO);

    List<CommentDTO> getCommentsByPostId(Long postId);

    CommentDTO getCommentById(Long postId, Long commentId);

    CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO);

    CommentDTO deleteComment(Long postId, Long commentId);

}
