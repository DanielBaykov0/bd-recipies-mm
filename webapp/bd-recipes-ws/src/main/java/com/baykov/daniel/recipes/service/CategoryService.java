//package com.baykov.daniel.recipes.service;
//
//import com.baykov.daniel.recipes.entity.Category;
//import com.baykov.daniel.recipes.exception.EntityAlreadyExistsException;
//import com.baykov.daniel.recipes.exception.ResourceNotFoundException;
//import com.baykov.daniel.recipes.payload.GenericPageableResponse;
//import com.baykov.daniel.recipes.payload.response.ResponseMessage;
//import com.baykov.daniel.recipes.repository.CategoryRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class CategoryService {
//
//    private final CategoryRepository categoryRepository;
//
//    public boolean doesCategoryExist(String name) {
//        return categoryRepository.findByName(name).isPresent();
//    }
//
//    public Category findCategoryById(Long id) {
//        return categoryRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(id));
//    }
//
//    public GenericPageableResponse<Category> findAllCategoriesPaged(
//            int pageNo, int pageSize, String sortBy, String sortDir) {
//        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//        Page<Category> categories = categoryRepository.findAll(pageable);
//        return new GenericPageableResponse<>(categories, categories.stream().toList());
//    }
//
//    @Transactional
//    public ResponseMessage addNewCategory(String name) {
//        Category category = categoryRepository.findByName(name)
//                        .orElseThrow(() -> new EntityAlreadyExistsException(name));
//        categoryRepository.save(category);
//        return ResponseMessage.success();
//    }
//
//    @Transactional
//    public ResponseMessage updateCategory(Long categoryId, Category category) {
//        Category existingCategory = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new ResourceNotFoundException(categoryId));
//
//        if (existingCategory.getName().name().equalsIgnoreCase(category.getName().name())) {
//            throw new EntityAlreadyExistsException(category.getName().name());
//        }
//
//        existingCategory.setName(category.getName());
//        categoryRepository.save(existingCategory);
//        return ResponseMessage.success();
//    }
//
//    @Transactional
//    public ResponseMessage deleteCategoryById(Long categoryId) {
//        Category category = categoryRepository.findById(categoryId)
//                        .orElseThrow(() -> new ResourceNotFoundException(categoryId));
//        categoryRepository.deleteById(category.getId());
//        return ResponseMessage.success();
//    }
//}
