package com.baykov.daniel.recipes.repository;

import com.baykov.daniel.recipes.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
