package com.baykov.daniel.lang.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "translations")
public class Translation {

    @EmbeddedId
    private TranslationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code", referencedColumnName = "code", insertable = false, updatable = false)
    private Language language;

    @Column(nullable = false, length = 255)
    private String value;
}
