package com.baykov.daniel.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String name) {
        super("Entity " + name + " already exists.");
    }
}
