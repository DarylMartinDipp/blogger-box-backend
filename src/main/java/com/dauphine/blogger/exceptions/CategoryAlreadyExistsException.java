package com.dauphine.blogger.exceptions;

public class CategoryAlreadyExistsException extends Exception {
    public CategoryAlreadyExistsException (String categoryName) {
        super("The category named " + categoryName + " already exists.");
    }
}
