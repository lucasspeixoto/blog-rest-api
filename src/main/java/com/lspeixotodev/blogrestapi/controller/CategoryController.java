package com.lspeixotodev.blogrestapi.controller;

import com.lspeixotodev.blogrestapi.dto.CategoryDTO;
import com.lspeixotodev.blogrestapi.dto.CategoryResponse;
import com.lspeixotodev.blogrestapi.service.CategoryService;
import com.lspeixotodev.blogrestapi.utils.AppConstants;
import com.lspeixotodev.blogrestapi.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON)
@Tag(
        name = "CRUD REST APIs for Category Resource"
)
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Operation(
            summary = "Create Category REST API",
            description = "Create Category REST API is used to save category into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {

        return new ResponseEntity<>(service.createCategory(categoryDTO), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get all Categories",
            description = "Get all Categories REST API is used to list all existing categories into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping
    public CategoryResponse getAllCategories(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir

    ) {

        return service.getAllCategories(pageNo, pageSize, sortBy, sortDir);
    }

    @Operation(
            summary = "Get Category By Id",
            description = "Get Category By Id REST API is used to get a post by id into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable(name = "id") Long id) throws Exception {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @Operation(
            summary = "Update a Category",
            description = "Update Category REST API is used to edit an existing category into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable(name = "id") Long id) {

        CategoryDTO responseCategoryDTO = service.updateCategory(categoryDTO, id);

        return new ResponseEntity<>(responseCategoryDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Category By Id",
            description = "Delete Category By Id REST API is used to remove a category by id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable(name = "id") Long id) {

        CategoryDTO responseCategoryDTO = service.deleteCategory(id);

        return new ResponseEntity<>(responseCategoryDTO, HttpStatus.OK);
    }

}
