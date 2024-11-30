package com.baykov.daniel.lang.repository;

import com.baykov.daniel.lang.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
