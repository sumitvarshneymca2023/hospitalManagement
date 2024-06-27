package com.hospital.management.Exception;

public class GenericNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GenericNotFoundException(String msg) {
        super(msg);
    }
}
