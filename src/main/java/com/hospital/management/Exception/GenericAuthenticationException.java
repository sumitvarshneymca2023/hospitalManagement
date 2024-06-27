package com.hospital.management.Exception;

public class GenericAuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GenericAuthenticationException(String msg) {
        super(msg);
    }
}
