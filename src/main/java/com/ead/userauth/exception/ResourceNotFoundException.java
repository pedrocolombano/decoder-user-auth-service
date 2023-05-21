package com.ead.userauth.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1962296658719741999L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
