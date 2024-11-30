package com.baykov.daniel.lang.repository;

import com.baykov.daniel.lang.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<Translation, Long> {

}
