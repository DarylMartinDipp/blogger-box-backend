package com.dauphine.blogger.services.impl.exceptions;

import java.util.UUID;

public class CategoryNotFoundByNameException extends Exception {
    public CategoryNotFoundByNameException(UUID uuid) {
        super(String.valueOf(uuid));
    }
}
