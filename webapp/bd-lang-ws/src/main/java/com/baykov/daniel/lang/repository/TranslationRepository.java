package com.baykov.daniel.lang.repository;

import com.baykov.daniel.lang.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TranslationRepository extends JpaRepository<Translation, Long> {

    Optional<List<Translation>> findByIdCode(String languageCode);
}
