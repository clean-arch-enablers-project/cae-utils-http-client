package io.github.julucinho.httpclient.impl.exceptions;

import lombok.Getter;

@Getter
public class RetryNeededOnExceptionThrownException extends RuntimeException{

    private final Class<? extends Exception> exceptionType;

    public RetryNeededOnExceptionThrownException(Class<? extends Exception> exceptionType){
        super("Retry needed on ".concat(exceptionType.getName()).concat(" being thrown"));
        this.exceptionType = exceptionType;
    }

}
