package com.lspeixotodev.blogrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Set;

@Schema(
        description = "PostDto Model Information"
)
public class PostDTO {

    private Long id;

    @Schema(
            description = "Blog Post Title"
    )
    @NotEmpty(message = "Title should not be null or empty")
    @Size(min = 2, message = "Post Title must be minimum 2 characters")
    private String title;

    @Schema(
            description = "Blog Post Description"
    )
    @NotEmpty(message = "Description should not be null or empty")
    @Size(min = 10, message = "Description must be minimum 10 characters")
    private String description;

    @Schema(
            description = "Blog Post Content"
    )
    @NotEmpty(message = "Content should not be null or empty")
    @Size(min = 10, message = "Content must be minimum 10 characters")
    private String content;

    private Set<CommentDTO> comments;

    @Schema(
            description = "Blog Post Category"
    )
    private Long categoryId;


    public PostDTO() {
    }

    public PostDTO(Long id, String title, String description, String content, Set<CommentDTO> comments, Long categoryId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.comments = comments;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostDTO postDTO)) return false;
        return Objects.equals(getId(), postDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
