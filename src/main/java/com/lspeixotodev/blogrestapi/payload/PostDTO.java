package com.lspeixotodev.blogrestapi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class PostDTO {

    private Long id;

    @NotEmpty(message = "Title should not be null or empty")
    @Size(min = 2, message = "Post Title must be minimum 2 characters")
    private String title;

    @NotEmpty(message = "Description should not be null or empty")
    @Size(min = 10, message = "Description must be minimum 10 characters")
    private String description;

    @NotEmpty(message = "Content should not be null or empty")
    @Size(min = 10, message = "Content must be minimum 10 characters")
    private String content;

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
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
