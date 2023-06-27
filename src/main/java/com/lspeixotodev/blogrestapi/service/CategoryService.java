package com.lspeixotodev.blogrestapi.service;

import com.lspeixotodev.blogrestapi.dto.CategoryDTO;
import com.lspeixotodev.blogrestapi.dto.CategoryResponse;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryResponse getAllCategories(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

    CategoryDTO getCategoryById(Long id);

    CategoryDTO updateCategory(CategoryDTO postDto, Long id);

    CategoryDTO deleteCategory(Long id);
}
