package com.cae.http_client.implementations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import static java.util.Optional.ofNullable;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionThrownByHttpRequestChecker {

    private final AbstractHttpRequestModel httpRequestModel;

    public static ExceptionThrownByHttpRequestChecker of(AbstractHttpRequestModel httpRequestModel) {
        return new ExceptionThrownByHttpRequestChecker(httpRequestModel);
    }

    public void checkOn(Exception e) {
        var handlerByThisException = ofNullable(this.httpRequestModel.exceptionHandlersByExceptionType.get(e.getClass()));
        handlerByThisException.ifPresent(handler -> handler.handle(e));
    }
}
