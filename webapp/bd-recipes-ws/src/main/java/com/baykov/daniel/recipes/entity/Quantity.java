package com.baykov.daniel.recipes.entity;

import com.baykov.daniel.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "quantities")
public class Quantity extends BaseEntity {

    //    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    //    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    //    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "measure_id")
    private Measure measure;

    //    @Column(nullable = false)
    private double ingredientQuantity;
}
