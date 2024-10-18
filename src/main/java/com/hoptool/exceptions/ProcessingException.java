package com.hoptool.exceptions;

public class ProcessingException extends RuntimeException implements java.io.Serializable {

    public ProcessingException(String message) {
        super(message);
    }
}
