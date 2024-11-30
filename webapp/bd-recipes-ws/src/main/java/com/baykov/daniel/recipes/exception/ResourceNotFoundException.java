package com.baykov.daniel.recipes.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long resourceId) {
        super("Cannot find resource with id " + resourceId);
    }

    public ResourceNotFoundException(String name) {
        super("Cannot find resource with name " + name);
    }
}
