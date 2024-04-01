package io.github.julucinho.httpclient.impl.exceptions;

public class InterruptedRuntimeException extends RuntimeException {
    public InterruptedRuntimeException(InterruptedException e) {
        super(e.getMessage());
    }
}
