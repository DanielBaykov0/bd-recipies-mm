//package com.baykov.daniel.recipes.bootstrap;
//
//import com.baykov.daniel.recipes.entity.constant.CategoryEnum;
//import com.baykov.daniel.recipes.entity.constant.MeasureUnitEnum;
//import com.baykov.daniel.recipes.entity.Category;
//import com.baykov.daniel.recipes.entity.Ingredient;
//import com.baykov.daniel.recipes.entity.Measure;
//import com.baykov.daniel.recipes.repository.CategoryRepository;
//import com.baykov.daniel.recipes.repository.IngredientRepository;
//import com.baykov.daniel.recipes.repository.MeasureRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class Data implements CommandLineRunner {
//
//    private final CategoryRepository categoryRepository;
//    private final IngredientRepository ingredientRepository;
//    private final MeasureRepository measureRepository;
//    private final
//
//    @Override
//    public void run(String... args) {
//
//        if (categoryRepository.count() == 0) {
//            loadCategoryData();
//        }
//
//        if (ingredientRepository.count() == 0) {
//            loadIngredientData();
//        }
//
//        if (measureRepository.count() == 0) {
//            loadMeasureData();
//        }
//    }
//
//    private void loadCategoryData() {
//        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
//            categoryRepository.save(
//                    Category.builder()
//                            .name(categoryEnum)
//                            .build()
//            );
//        }
//    }
//
//    private void loadIngredientData() {
//        List<String> ingredientNames = List.of(
//                "Onion", "Cucumber", "Mushroom", "Tomato",
//                "Spinach", "Lettuce", "Green Peas", "Pumpkin"
//        );
//
//        ingredientNames.forEach(name -> {
//            Ingredient ingredient = Ingredient.builder()
//                    .name(name)
//                    .build();
//            ingredientRepository.save(ingredient);
//        });
//    }
//
//    private void loadMeasureData() {
//        for (MeasureUnitEnum measureUnitEnum : MeasureUnitEnum.values()) {
//            measureRepository.save(
//                    Measure.builder()
//                            .name(measureUnitEnum)
//                            .build()
//            );
//        }
//    }
//}
