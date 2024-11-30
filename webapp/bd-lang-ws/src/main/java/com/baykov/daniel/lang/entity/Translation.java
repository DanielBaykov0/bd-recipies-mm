package com.baykov.daniel.lang.entity;

import com.baykov.daniel.entity.BaseEntity;
import com.baykov.daniel.recipes.entity.Language;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "translations",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"key", "language_id"}
        )
)
public class Translation extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String key;

    @Column(nullable = false, length = 255)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;
}