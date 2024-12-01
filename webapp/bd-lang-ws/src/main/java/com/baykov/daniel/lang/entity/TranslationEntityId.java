package com.baykov.daniel.lang.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Embeddable
public class TranslationEntityId implements Serializable {

    @Column(nullable = false, length = 255)
    private String key;

    @Column(nullable = false, length = 10)
    private String code;

    @Column(nullable = false, length = 1)
    private String isActive;
}
