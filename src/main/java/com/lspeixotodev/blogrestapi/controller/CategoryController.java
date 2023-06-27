package com.lspeixotodev.blogrestapi.controller;

import com.lspeixotodev.blogrestapi.dto.CategoryDTO;
import com.lspeixotodev.blogrestapi.dto.CategoryResponse;
import com.lspeixotodev.blogrestapi.service.CategoryService;
import com.lspeixotodev.blogrestapi.utils.AppConstants;
import com.lspeixotodev.blogrestapi.utils.MediaType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON)
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {

        return new ResponseEntity<>(service.createCategory(categoryDTO), HttpStatus.CREATED);
    }


    @GetMapping
    public CategoryResponse getAllCategories(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir

    ) {

        return service.getAllCategories(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable(name = "id") Long id) throws Exception {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable(name = "id") Long id) {

        CategoryDTO responseCategoryDTO = service.updateCategory(categoryDTO, id);

        return new ResponseEntity<>(responseCategoryDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable(name = "id") Long id) {

        CategoryDTO responseCategoryDTO = service.deleteCategory(id);

        return new ResponseEntity<>(responseCategoryDTO, HttpStatus.OK);
    }

}
