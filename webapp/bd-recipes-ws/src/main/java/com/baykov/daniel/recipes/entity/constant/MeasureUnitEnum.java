package com.baykov.daniel.recipes.entity.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MeasureUnitEnum {
    // Volume
    CUP,
    TEASPOON,
    TABLESPOON,
    LITER,
    MILLILITER,
    GALLON,
    QUART,
    PINT,
    FLUID_OUNCE,

    // Weight
    GRAM,
    KILOGRAM,
    MILLIGRAM,
    OUNCE,
    POUND,

    // Countable
    EGG,
    PIECE,
    SLICE,
    PACK,

    // Miscellaneous
    PINCH,
    DASH,
    DROP,
    HANDFUL;

    @JsonCreator
    public static MeasureUnitEnum fromValue(String value) {
        for (MeasureUnitEnum measure : MeasureUnitEnum.values()) {
            if (measure.name().equalsIgnoreCase(value)) {
                return measure;
            }
        }
        throw new IllegalArgumentException("Invalid MeasureEnum value: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
