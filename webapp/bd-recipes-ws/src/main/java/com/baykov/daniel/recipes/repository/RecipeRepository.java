package com.baykov.daniel.recipes.repository;

import com.baykov.daniel.recipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
