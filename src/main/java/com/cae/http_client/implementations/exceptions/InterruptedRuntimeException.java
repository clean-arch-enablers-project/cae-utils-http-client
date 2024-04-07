package com.cae.http_client.implementations.exceptions;

public class InterruptedRuntimeException extends RuntimeException {
    public InterruptedRuntimeException(InterruptedException e) {
        super(e.getMessage());
    }
}
