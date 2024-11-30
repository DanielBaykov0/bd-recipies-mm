package com.baykov.daniel.recipes.repository;

import com.baykov.daniel.recipes.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<Translation, Long> {
}
