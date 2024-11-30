//package com.baykov.daniel.recipes.service;
//
//import com.baykov.daniel.recipes.entity.Ingredient;
//import com.baykov.daniel.recipes.exception.EntityAlreadyExistsException;
//import com.baykov.daniel.recipes.exception.ResourceNotFoundException;
//import com.baykov.daniel.recipes.payload.GenericPageableResponse;
//import com.baykov.daniel.recipes.payload.response.ResponseMessage;
//import com.baykov.daniel.recipes.repository.IngredientRepository;
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
//public class IngredientService {
//
//    private final IngredientRepository ingredientRepository;
//
//    public boolean doesIngredientExist(String name) {
//        return ingredientRepository.findByName(name).isPresent();
//    }
//
//    public Ingredient findIngredientById(Long id) {
//        return ingredientRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(id));
//    }
//
//    public GenericPageableResponse<Ingredient> findAllIngredientsPaged(
//            int pageNo, int pageSize, String sortBy, String sortDir) {
//        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//        Page<Ingredient> ingredients = ingredientRepository.findAll(pageable);
//        return new GenericPageableResponse<>(ingredients, ingredients.stream().toList());
//    }
//
//    @Transactional
//    public ResponseMessage addNewIngredient(String name) {
//        Ingredient ingredient = ingredientRepository.findByName(name)
//                .orElseThrow(() -> new EntityAlreadyExistsException(name));
//        ingredientRepository.save(ingredient);
//        return ResponseMessage.success();
//    }
//
//    @Transactional
//    public ResponseMessage updateIngredientById(Long ingredientId, Ingredient ingredient) {
//        Ingredient exsistingIngredient = ingredientRepository.findById(ingredientId)
//                .orElseThrow(() -> new ResourceNotFoundException(ingredientId));
//
//        if (exsistingIngredient.getName().equalsIgnoreCase(ingredient.getName())) {
//            throw new EntityAlreadyExistsException(ingredient.getName());
//        }
//
//        exsistingIngredient.setName(ingredient.getName());
//        ingredientRepository.save(exsistingIngredient);
//        return ResponseMessage.success();
//    }
//
//    @Transactional
//    public ResponseMessage deleteIngredientById(Long ingredientId) {
//        Ingredient ingredient = ingredientRepository.findById(ingredientId)
//                .orElseThrow(() -> new ResourceNotFoundException(ingredientId));
//        ingredientRepository.deleteById(ingredient.getId());
//        return ResponseMessage.success();
//    }
//}
