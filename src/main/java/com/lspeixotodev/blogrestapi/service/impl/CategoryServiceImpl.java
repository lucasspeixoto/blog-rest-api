package com.lspeixotodev.blogrestapi.service.impl;

import com.lspeixotodev.blogrestapi.dto.CategoryDTO;
import com.lspeixotodev.blogrestapi.dto.CategoryResponse;
import com.lspeixotodev.blogrestapi.entity.Category;
import com.lspeixotodev.blogrestapi.exception.ResourceNotFoundException;
import com.lspeixotodev.blogrestapi.mappers.BlogMapper;
import com.lspeixotodev.blogrestapi.repository.CategoryRepository;
import com.lspeixotodev.blogrestapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = this.convertDTOToEntity(categoryDTO);

        Category newCategory = this.repository.save(category);


        return this.convertEntityToDTO(newCategory);
    }

    @Override
    public CategoryResponse getAllCategories(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(
                pageNo,
                pageSize,
                sort
        );

        Page<Category> pageablePosts = this.repository.findAll(pageable);

        List<Category> pageableCategoriesContent = pageablePosts.getContent();

        List<CategoryDTO> content = convertEntitiesToDTOs(pageableCategoriesContent);

        CategoryResponse response = new CategoryResponse();

        response.setContent(content);
        response.setPageNo(pageablePosts.getNumber());
        response.setPageSize(pageablePosts.getSize());
        response.setTotalElements(pageablePosts.getTotalElements());
        response.setTotalPages(pageablePosts.getTotalPages());
        response.setLast(pageablePosts.isLast());

        return response;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category selectedCategory = this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id)
        );

        return convertEntityToDTO(selectedCategory);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDto, Long id) {
        Category selectedCategory = this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id)
        );

        selectedCategory.setName(categoryDto.getName());
        selectedCategory.setDescription(categoryDto.getDescription());

        Category updatedCategory = this.repository.save(selectedCategory);

        return this.convertEntityToDTO(updatedCategory);

    }

    @Override
    public CategoryDTO deleteCategory(Long id) {
        Category selectedCategory = this.repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id)
        );

        this.repository.delete(selectedCategory);

        return this.convertEntityToDTO(selectedCategory);

    }


    //! Mapper methods ------------------------------------------
    private CategoryDTO convertEntityToDTO(Category category) {
        return BlogMapper.parseObject(category, CategoryDTO.class, modelMapper);
    }

    private Category convertDTOToEntity(CategoryDTO categoriesDTO) {
        return BlogMapper.parseObject(categoriesDTO, Category.class, modelMapper);
    }

    private List<CategoryDTO> convertEntitiesToDTOs(List<Category> entities) {
        return BlogMapper.parseListObjects(entities, CategoryDTO.class, modelMapper);
    }

    private List<Category> convertDTOsToEntities(List<CategoryDTO> categories) {
        return BlogMapper.parseListObjects(categories, Category.class, modelMapper);
    }
    //! ------------------------------------------ Mapper methods
}
