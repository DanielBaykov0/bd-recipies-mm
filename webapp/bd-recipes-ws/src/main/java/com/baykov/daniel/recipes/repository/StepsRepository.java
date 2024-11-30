package com.baykov.daniel.recipes.repository;

import com.baykov.daniel.recipes.entity.Steps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepsRepository extends JpaRepository<Steps, Long> {
}
