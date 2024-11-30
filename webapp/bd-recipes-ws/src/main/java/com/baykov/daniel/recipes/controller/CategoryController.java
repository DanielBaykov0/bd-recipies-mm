package com.baykov.daniel.recipes.controller;

import com.baykov.daniel.recipes.entity.Category;
import com.baykov.daniel.recipes.payload.GenericPageableResponse;
import com.baykov.daniel.recipes.payload.response.ResponseMessage;
import com.baykov.daniel.recipes.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Category category = categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<GenericPageableResponse<Category>> getAllCategories(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

        GenericPageableResponse<Category> categoriesResponse = categoryService.findAllCategoriesPaged(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(categoriesResponse);
    }

//    @PostMapping
//    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO authorRequestDTO) {
//        log.info("Correlation ID: {}. Received request to create a new author: {}", RequestData.getCorrelationId(), authorRequestDTO);
//
//        AuthorResponseDTO createdAuthor = authorService.createAuthor(authorRequestDTO);
//
//        log.info("Correlation ID: {}. Author created successfully: {}", RequestData.getCorrelationId(), createdAuthor);
//        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
//    }

    @Operation(hidden = true)
    @PutMapping("/{categoryId}")
    public ResponseEntity<ResponseMessage> updateCategoryById(
            @PathVariable Long categoryId,
            @Valid @RequestBody Category category) {
        ResponseMessage response = categoryService.updateCategory(categoryId, category);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ResponseMessage> deleteCategoryById(@PathVariable Long categoryId) {
        ResponseMessage response = categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok(response);
    }
}
