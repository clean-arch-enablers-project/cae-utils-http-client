package com.clean_arch_enablers.http_client.implementations.exceptions;

public class InterruptedRuntimeException extends RuntimeException {
    public InterruptedRuntimeException(InterruptedException e) {
        super(e.getMessage());
    }
}
