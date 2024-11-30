package com.baykov.daniel.recipes.entity;

import com.baykov.daniel.recipes.entity.constant.MeasureUnitEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "measures")
public class Measure extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private MeasureUnitEnum name;
}
