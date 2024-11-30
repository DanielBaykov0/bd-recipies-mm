package com.baykov.daniel.recipes.entity.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoryEnum {
    SOUP, SALAD, DESERT, MAIN_DISH;

    @JsonCreator
    public static CategoryEnum fromValue(String value) {
        for (CategoryEnum category : CategoryEnum.values()) {
            if (category.name().equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid CategoryEnum value: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
