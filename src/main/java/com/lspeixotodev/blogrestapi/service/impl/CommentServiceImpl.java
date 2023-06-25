package com.lspeixotodev.blogrestapi.service.impl;


import com.lspeixotodev.blogrestapi.entity.Comment;
import com.lspeixotodev.blogrestapi.entity.Post;
import com.lspeixotodev.blogrestapi.exception.BlogAPIException;
import com.lspeixotodev.blogrestapi.exception.ResourceNotFoundException;
import com.lspeixotodev.blogrestapi.mappers.BlogMapper;
import com.lspeixotodev.blogrestapi.dto.CommentDTO;
import com.lspeixotodev.blogrestapi.repository.CommentRepository;
import com.lspeixotodev.blogrestapi.repository.PostRepository;
import com.lspeixotodev.blogrestapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {

        //Create the Comment from CommentDTO
        Comment comment = this.convertDTOToEntity(commentDTO);

        //Find the post for associate
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        comment.setPost(post);

        // comment entity to DB
        Comment newComment =  commentRepository.save(comment);

        return this.convertEntityToDTO(comment);

    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);

        return this.convertEntitiesToDTOs(comments);

    }

    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {

        Comment comment = getPostCommentAndCheck(postId, commentId);

        return this.convertEntityToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {

        Comment comment = getPostCommentAndCheck(postId, commentId);

        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());

        Comment newComment = this.commentRepository.save(comment);

        return this.convertEntityToDTO(newComment);

    }

    @Override
    public CommentDTO deleteComment(Long postId, Long commentId) {
        Comment comment = getPostCommentAndCheck(postId, commentId);

        this.commentRepository.delete(comment);

        return this.convertEntityToDTO(comment);
    }

    private Comment getPostCommentAndCheck(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return comment;
    }


    //! Mapper methods ------------------------------------------
    private CommentDTO convertEntityToDTO(Comment entity) {
        return BlogMapper.parseObject(entity, CommentDTO.class, modelMapper);
    }

    private Comment convertDTOToEntity(CommentDTO commentDTO) {
        return BlogMapper.parseObject(commentDTO, Comment.class, modelMapper);
    }

    private List<CommentDTO> convertEntitiesToDTOs(List<Comment> entities) {
        return BlogMapper.parseListObjects(entities, CommentDTO.class, modelMapper);
    }

    private List<Comment> convertDTOsToEntities(List<CommentDTO> commentDTO) {
        return BlogMapper.parseListObjects(commentDTO, Comment.class, modelMapper);
    }
    //! ------------------------------------------ Mapper methods
}
