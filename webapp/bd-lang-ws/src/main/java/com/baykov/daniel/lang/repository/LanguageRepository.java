package com.baykov.daniel.lang.repository;

import com.baykov.daniel.lang.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {
}
