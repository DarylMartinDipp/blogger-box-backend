package com.dauphine.blogger.exceptions;

import java.util.UUID;

public class PostNotFoundByIdException extends Exception{
    public PostNotFoundByIdException (UUID postId) {
        super("The post designated by id " + postId + " was not found.");
    }
}
