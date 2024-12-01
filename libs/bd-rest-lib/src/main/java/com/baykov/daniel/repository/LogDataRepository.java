package com.baykov.daniel.repository;

import com.baykov.daniel.entity.LogDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDataRepository extends JpaRepository<LogDataEntity, Long> {
}
