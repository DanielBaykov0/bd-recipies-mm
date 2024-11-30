package com.baykov.daniel.lang.entity;

import com.baykov.daniel.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
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
