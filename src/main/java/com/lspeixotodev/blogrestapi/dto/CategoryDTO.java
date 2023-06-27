package com.lspeixotodev.blogrestapi.dto;

import java.util.*;


public class CategoryDTO {


    private Long id;


    private String name;


    private String description;


    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDTO category)) return false;
        return getId().equals(category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

