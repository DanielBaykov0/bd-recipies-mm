package com.baykov.daniel.recipes.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String name) {
        super("Entity " + name + " already exists.");
    }
}
