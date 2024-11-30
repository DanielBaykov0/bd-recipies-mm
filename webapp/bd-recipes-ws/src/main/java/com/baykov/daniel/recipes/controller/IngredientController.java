//package com.baykov.daniel.recipes.controller;
//
//import com.baykov.daniel.recipes.entity.Ingredient;
//import com.baykov.daniel.recipes.payload.GenericPageableResponse;
//import com.baykov.daniel.recipes.payload.response.ResponseMessage;
//import com.baykov.daniel.recipes.service.IngredientService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("api/v1/ingredients")
//@RequiredArgsConstructor
//public class IngredientController {
//
//    private final IngredientService ingredientService;
//
//    @GetMapping("/{ingredientId}")
//    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long ingredientId) {
//        Ingredient ingredient = ingredientService.findIngredientById(ingredientId);
//        return ResponseEntity.ok(ingredient);
//    }
//
//    @GetMapping
//    public ResponseEntity<GenericPageableResponse<Ingredient>> getAllIngredients(
//            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
//            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
//            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
//
//        GenericPageableResponse<Ingredient> ingredientsResponse = ingredientService.findAllIngredientsPaged(pageNo, pageSize, sortBy, sortDir);
//        return ResponseEntity.ok(ingredientsResponse);
//    }
//
////    @PostMapping
////    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO authorRequestDTO) {
////        log.info("Correlation ID: {}. Received request to create a new author: {}", RequestData.getCorrelationId(), authorRequestDTO);
////
////        AuthorResponseDTO createdAuthor = authorService.createAuthor(authorRequestDTO);
////
////        log.info("Correlation ID: {}. Author created successfully: {}", RequestData.getCorrelationId(), createdAuthor);
////        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
////    }
//
//    @PutMapping("/{ingredientId}")
//    public ResponseEntity<ResponseMessage> updateIngredientById(
//            @PathVariable Long ingredientId,
//            @Valid @RequestBody Ingredient ingredient) {
//        ResponseMessage response = ingredientService.updateIngredientById(ingredientId, ingredient);
//        return ResponseEntity.ok(response);
//    }
//
//    @DeleteMapping("/{ingredientId}")
//    public ResponseEntity<ResponseMessage> deleteIngredientById(@PathVariable Long ingredientId) {
//        ResponseMessage response = ingredientService.deleteIngredientById(ingredientId);
//        return ResponseEntity.ok(response);
//    }
//}
