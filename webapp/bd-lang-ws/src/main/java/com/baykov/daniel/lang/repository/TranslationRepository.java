package com.baykov.daniel.lang.repository;

import com.baykov.daniel.lang.entity.TranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TranslationRepository extends JpaRepository<TranslationEntity, Long> {

    Optional<List<TranslationEntity>> findByIdCode(String languageCode);
}
