package com.baykov.daniel.recipes.entity;

import com.baykov.daniel.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "recipes")
public class Recipe extends BaseEntity {

    //    @Column(nullable = false)
    private String name;

    //    @Column(nullable = false)
    private String description;

    //    @Column(nullable = false)
    private String instructions;
}
